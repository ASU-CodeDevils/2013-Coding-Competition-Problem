package com.sf.codingcomp.duck;

public class DuckFinder {

	public static <T> boolean isADuck(T objectToCheck) {
		
		try {
		  objectToCheck.getClass().getMethod("looksLikeADuck");
		  objectToCheck.getClass().getMethod("quacksLikeADuck");
		  
		  
		} catch (NoSuchMethodException | SecurityException e) {
		  return false;
		}
		return true;
	}

}
