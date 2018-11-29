package com.trummer.javarestarttraining.business.entities;

public class PoJo {

	int myInt;

	public PoJo() {
		System.out.println("PoJo parameterless contructor.");
		myInt = 0;
	}

	public PoJo(int initInt) {
		System.out.println("PoJo parmatized constructor.");
		myInt = initInt;
	}

	int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

}