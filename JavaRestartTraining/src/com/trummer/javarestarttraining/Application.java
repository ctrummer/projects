/**
 *
 */
package com.trummer.javarestarttraining;

import com.trummer.javarestarttraining.business.entities.Car;
import com.trummer.javarestarttraining.business.entities.PoJo;

/**
 * @author chris
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println("Hello iMac!");
		final PoJo pojo = new PoJo();
		pojo.setMyInt(45);

		final Car myFirstCar = new Car(100, "Opel");
		myFirstCar.showCar();

		if (myFirstCar.getManufacture() == "Opel" && myFirstCar.getPower() == 100) {
			myFirstCar.setPower(135);
			myFirstCar.setManufacture("VW");
		}
		myFirstCar.showCar();

	}

	private int myInt;

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(final int myInt) {
		this.myInt = myInt;
	}

}
