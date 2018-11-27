package com.trummer.javarestarttraining.business.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Car {

	private static final Logger logger = LogManager.getLogger(Car.class);

	private int power;

	private String manufacture;

	public Car(final int power, final String manufacture) {
		super();
		this.power = power;
		this.manufacture = manufacture;
		logger.error("New Car - Error!!!!");
		logger.warn("New Car - Warn!!!!");
		logger.info("New Car - Info!!!!");
		logger.debug("New Car - Debug!!!!");
		logger.trace("New Car - Trace!!!!");

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
