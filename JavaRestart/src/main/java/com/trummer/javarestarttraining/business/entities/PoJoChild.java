package com.trummer.javarestarttraining.business.entities;

public class PoJoChild extends PoJo {

	public PoJoChild(int intValue) {
//			super(intValue);

		myInt = intValue;
		System.out.println("PoJoChild parameterized constructor.");
	}

}
