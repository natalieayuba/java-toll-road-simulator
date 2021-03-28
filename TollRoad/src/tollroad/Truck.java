/*
 * Truck.java
 * This class represents a Truck object which extends the Vehicle object, with a registration number, manufacturer
 * and payload (amount of cargo in kg that a van can store).
 * @author: Natalie Ayuba
 */

package tollroad;

public class Truck extends Vehicle {

    private final int numTrailers; // number of trailers a truck is fitted with

    public Truck(String regNum, String manufacturer, int numTrailers) {
        super(regNum, manufacturer);
        this.numTrailers = numTrailers;
    }

    // Accessor method
    public int getNumTrailers() {
        return numTrailers;
    }
        
    @Override
    public int calculateBasicTripCost() {
        if(numTrailers == 0 || numTrailers == 1) {
            return 1250;
        } else {
            return 1500;
        }
    }
       
    @Override
    public String toString() {
        String str = " trailers";
        if(numTrailers == 1) {
            str = " trailer";
        }
        return this.getManufacturer() + ", " + this.getRegNum() + ", " + numTrailers + str;
    }
    
    //--------------------------------------------------------------------------
    // Test harness
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) {
        Truck t1 = new Truck("TE56 XDS", "Citroen", 0);
        System.out.println("Truck #1: " + t1);
        // Testing accessors
        System.out.println("Manufacturer: " + t1.getManufacturer());
        System.out.println("Registration number: " + t1.getRegNum());
        System.out.println("Number of trailers: " + t1.getNumTrailers());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + t1.calculateBasicTripCost()); // expected value = 1250
            
        System.out.println();

        Truck t2 = new Truck("RW69 DFS", "BMW", 1);
        System.out.println("Truck #2: " + t2);
        // Testing accessors
        System.out.println("Manufacturer: " + t2.getManufacturer());
        System.out.println("Registration number: " + t2.getRegNum());
        System.out.println("Number of trailers: " + t2.getNumTrailers());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + t2.calculateBasicTripCost()); // expected value = 1250

        System.out.println();

        Truck t3 = new Truck("YH45 NAT", "Audi", 2);
        System.out.println("Truck #3: " + t3);
        // Testing accessors
        System.out.println("Manufacturer: " + t3.getManufacturer());
        System.out.println("Registration Number: " + t3.getRegNum());
        System.out.println("Number of trailers: " + t3.getNumTrailers());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + t3.calculateBasicTripCost()); // expected value = 1500
    }
}   
