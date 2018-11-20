package com.trummer.javarestarttraining.business.entities;

public class Car {

	private int power;

	private String manufacture;

	public Car() {
		power = 0;
		manufacture = "Unknown";
	}

	public String getManufacture() {
		return manufacture;
	}

	public int getPower() {
		return power;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void showCar() {
		System.out.println("Manufacture == " + manufacture + " Power == " + power);
	}

}
