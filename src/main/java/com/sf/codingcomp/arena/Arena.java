package com.sf.codingcomp.arena;

public class Arena {

	public Character fight(Character character1, Character character2) throws NoStaminaException {
		// TODO implement me
		if(character1.getStamina()<=0)
			throw new NoStaminaException();
		else
		{
			character1.setStamina(character1.getStamina()-1);
			double char2total = (character2.getDefense()-character1.getAttack());
			double char1total = ((character1.getDefense()*.75)-(character2.getAttack()*.75));
			if(char2total<=0)
				return(character1);
			else if(char1total<=0)
				return(character2);
			else if(char1total==char2total)
				return(character1);
			else if(char1total>char2total)
				return(character1);
			else 
				return(character2);
		}
		
	}
}
