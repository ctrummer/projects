package com.trummer.javarestarttraining.business.entities;

public class Empty {
	int integerEmpty;
	String stringEmpty;

	public void showEmpty() {
		System.out.println("Integer == " + integerEmpty + " String ==  " + stringEmpty);

		if (stringEmpty == null) {
			System.out.println("Halleluja!");
		}
	}

}
