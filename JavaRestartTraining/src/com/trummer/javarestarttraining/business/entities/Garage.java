package com.trummer.javarestarttraining.business.entities;

public class Garage {
	boolean doorOpen = false;

	public void closeGarage() {
		doorOpen = false;
	}

	public void openGarage() {
		doorOpen = true;
	}

}
