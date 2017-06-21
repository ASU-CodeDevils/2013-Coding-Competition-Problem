package com.sf.codingcomp.security;

import java.util.ArrayList;
import java.util.List;

public class SecurityMatrix {

	private static final Role ADMIN = new Role("ADMIN");
	private static final Role DEV = new Role("DEV");
	private static final Role TEST = new Role("TEST");
	private static final Role READONLY = new Role("READONLY");
	
	private static Role[] ROLES = new Role[10];
	private static int current = 0;
	

	static {
		try {
			createRole(ADMIN);
			createRole(DEV);
			createRole(TEST);
			createRole(READONLY);
			grantAccess(READONLY, Action.READ);
			grantAccess(DEV, Action.READ, Day.WEEKEND());
			grantAccess(TEST, Action.READ);
			grantAccess(TEST, Action.CREATE, Day.WORK_WEEK());
			grantAccess(TEST, Action.UPDATE, Day.WORK_WEEK());
			for (Action action : Action.values()) {
				grantAccess(ADMIN, action, Day.values());
				grantAccess(DEV, action, Day.WORK_WEEK());
			}
		} catch (RoleAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	public static void createRole(Role role) throws RoleAlreadyExistsException {
		
		if (current == ROLES.length) { 
			expandArray();
		}
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].getName().equalsIgnoreCase(role.getName())) {
				throw new RoleAlreadyExistsException();
			}
		}
		
		ROLES[current] = role;
		current++;
				
	}

	public static boolean hasAccess(Role role, Day day, Action action) {
		
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].getName().equalsIgnoreCase(role.getName())) { 
				return ROLES[i].hasAccess(day, action);
			}
		}
		return false;
		
	}

	public static List<Day> findDays(Role role, Action action) {
	
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].getName().equalsIgnoreCase(role.getName())) { 
				return ROLES[i].findDays(action);
			}
		}
		return null;
	}

	public static List<Action> findActions(Role role, Day day) {
		
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].getName().equalsIgnoreCase(role.getName())) { 
				return ROLES[i].findActions(day);
			}
		}

		return null;
	}

	public static List<Role> findRoles(Day day, Action action) {
		List<Role> list = new ArrayList<Role>();
		
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].hasAccess(day, action)) { 
				list.add(ROLES[i]);
			}
		}
		java.util.Collections.sort(list);
		return list;
	}

	public static void grantAccess(Role role, Action action, Day... days) {
		int index = -1;
		
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].getName().equalsIgnoreCase(role.getName())) { 
				index = i;
			}
		}
		
		if (index < 0) { 
			throw new NullPointerException();
		}
		for (Day d : days) { 
			ROLES[index].accessGranted(d, action);
		}
	}

	public static void revokeAccess(Role role, Action action, Day... days) {
		int index = -1;
		
		for (int i = 0; i < current; i++) { 
			if (ROLES[i].getName().equalsIgnoreCase(role.getName())) { 
				index = i;
			}
		}
		
		if (index < 0) { 
			throw new NullPointerException();
		}
		for (Day d : days) { 
			ROLES[index].accessRevoked(d, action);
		}
	}
	
	private static void expandArray() { 
		Role[] temp = new Role[ROLES.length * 2];
		
		for (int i = 0; i < current; i++) { 
			temp[i] = ROLES[i];
		}
		
		ROLES = temp;
	}

}
