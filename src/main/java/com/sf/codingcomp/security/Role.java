package com.sf.codingcomp.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Role implements Comparable<Role> {

	private static HashMap<Action, Integer> actionIndexMap;
	static { 
		actionIndexMap = new HashMap<Action, Integer>();
		actionIndexMap.put(Action.CREATE, 0);
		actionIndexMap.put(Action.READ, 1);
		actionIndexMap.put(Action.UPDATE, 2);
		actionIndexMap.put(Action.DELETE, 3);
	}
	private HashMap<Day, boolean[]> dayMap;
	private String name;
	

	public Role(String name) {
		this.name = name.toUpperCase();
		//Initialize dayMap for action storage
		this.dayMap = new HashMap<Day, boolean[]>();
		for (Day d : Day.values()) { 
			this.dayMap.put(d, new boolean[4]);
		}
	}
	
	//Grant access for input action on input day
	public void accessGranted(Day d, Action a) { 
		dayMap.get(d)[actionIndexMap.get(a)] = true;
		
	}
	
	//Grant access for all days for input action
	public void accessGranted(Action a) { 
		int index = actionIndexMap.get(a);
		for (Map.Entry<Day, boolean[]> entry : dayMap.entrySet()) { 
			entry.getValue()[index] = true;
		}
	}
	
	//Revoke access of input action on input day
	public void accessRevoked(Day d, Action a) { 
		dayMap.get(d)[actionIndexMap.get(a)] = false;
	}
	
	//Revoke access of input action on all days
	public void accessRevoked(Action a) { 
		int index = actionIndexMap.get(a);
		for (Map.Entry<Day, boolean[]> entry : dayMap.entrySet()) { 
			entry.getValue()[index] = false;
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//Check for access on certain day
	public boolean hasAccess(Day d, Action a) { 
		int index = actionIndexMap.get(a);
		return dayMap.get(d)[index];
		
	}
	
	//Returns list of days where Role has access to action input
	public List<Day> findDays(Action a) { 
		List<Day> list = new ArrayList<Day>();
		int index = actionIndexMap.get(a);
		
		for (Map.Entry<Day, boolean[]> entry : dayMap.entrySet()) { 
			if (entry.getValue()[index] == true) { 
				list.add(entry.getKey());
			}
		}
		return list;
	}
	
	//Returns list of Actions role has access to on input Day
	public List<Action> findActions(Day d) { 
		List<Action> list = new ArrayList<Action>();
		
		for (Map.Entry<Action, Integer> entry : actionIndexMap.entrySet()) { 
			if (dayMap.get(d)[entry.getValue()] == true) { 
				list.add(entry.getKey());
			}
		}
		return list;
	}

	public int compareTo(Role other) {
		return this.getName().compareTo(other.getName());
	}


}
