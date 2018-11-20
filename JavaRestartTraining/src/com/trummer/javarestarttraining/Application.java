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

	 private int myInt;

	    /**
	     * @param args
	     */
	    public static void main(String[] args) {
	        System.out.println("Hello iMac!");
	        PoJo pojo = new PoJo();
	        pojo.setMyInt(45);

	        Car  myFirstCar = new Car();
	        myFirstCar.setPower(100);
	        myFirstCar.setManufacture("Opel");
	        myFirstCar.showCar();

	        if (myFirstCar.getManufacture() == "Opel" && myFirstCar.getPower() == 100) {
	            myFirstCar.setPower(135);
	            myFirstCar.setManufacture("VW");
	        }
	        myFirstCar.showCar();


	    }

	    public int getMyInt() {
	        return myInt;
	    }

	    public void setMyInt(int myInt) {
	        this.myInt = myInt;
	    }

}
