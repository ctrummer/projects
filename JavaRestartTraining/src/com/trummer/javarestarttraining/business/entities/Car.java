package com.trummer.javarestarttraining.business.entities;

public class Car {

	private int power;

	private String manufacture;

	public Car(final int power, final String manufacture) {
		super();
		this.power = power;
		this.manufacture = manufacture;
	}

	public String getManufacture() {
		return manufacture;
	}

	public int getPower() {
		return power;
	}

	public void setManufacture(final String manufacture) {
		this.manufacture = manufacture;
	}

	public void setPower(final int power) {
		this.power = power;
	}

	public void showCar() {
		System.out.println("Manufacture == " + manufacture + " Power == " + power);
	}

}
