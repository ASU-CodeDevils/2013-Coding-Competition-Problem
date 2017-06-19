package com.sf.codingcomp.football;

import java.text.DecimalFormat;

public class Quarterback extends Player {

	public Quarterback(int touchdowns, int yardsGained, boolean active) {
		super(touchdowns, yardsGained, active);
	}

	@Override
	public double calculateTotalScore(boolean partialPointsAllowed) {
		double totalScore;
		DecimalFormat rounded = new DecimalFormat("###.00");
		if (partialPointsAllowed){
			totalScore= Double.valueOf(rounded.format((touchdowns*5)+(yardsGained/25)));
			return totalScore;
			}
			else
				return (touchdowns*5)+(yardsGained/25);
		}
}
