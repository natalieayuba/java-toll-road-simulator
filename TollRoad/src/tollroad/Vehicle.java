/*
* Vehicle.java
* This class represents a Vehicle object with a registration number and manufacturer.
* @author: Natalie Ayuba
*/

package tollroad;

public abstract class Vehicle {

    private final String regNum; // vehicle registration number
    private final String manufacturer; // vehicle make

    public Vehicle(String regNum, String manufacturer) {
        this.regNum = regNum;
        this.manufacturer = manufacturer;
    }

    // Accessor methods
    public String getRegNum() {
        return regNum;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    // Abstract method to be overridden by child classes
    public abstract int calculateBasicTripCost();
    
    @Override
    public String toString() {
        return manufacturer + ", " + regNum;
    }
}

