/*
 * Car.java
 * This class a Car object which extends the Vehicle object, with a registration number, manufacturer, and a number of
 * seats.
 * @author: Natalie Ayuba
 */

package tollroad;

public class Car extends Vehicle {

    private final int numberOfSeats;

    public Car(String regNum, String manufacturer, int numberOfSeats) {
        super(regNum, manufacturer);
        this.numberOfSeats = numberOfSeats;
    }

    // Accessor method
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public int calculateBasicTripCost() {
        if(numberOfSeats <= 5) {
            return 500;
        } else {
            return 600;
        }
    }
        
    @Override
    public String toString() {
        String str = " seats";
        if(numberOfSeats == 1) {
            str = " seat";
        }
        return this.getManufacturer() + ", " + this.getRegNum() + ", " + numberOfSeats + str;
    }
    
    //--------------------------------------------------------------------------
    // Test harness
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) {
        Car c1 = new Car("ND18 TWL", "Ford", 5);
        System.out.println("Car #1: " + c1);
        // Testing accessor methods
        System.out.println("Manufacturer: " + c1.getManufacturer());
        System.out.println("Registration number: " + c1.getRegNum());
        System.out.println("Number of seats: " + c1.getNumberOfSeats());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + c1.calculateBasicTripCost());  // expected value = 500

        System.out.println();

        Car c2 = new Car("SW57 FR9", "Fiat", 8);
        System.out.println("Car #2: " + c2);
        //Testing accessor methods
        System.out.println("Manufacturer: " + c2.getManufacturer());
        System.out.println("Registration number: " + c2.getRegNum());
        System.out.println("Number of seats: " + c2.getNumberOfSeats());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + c2.calculateBasicTripCost()); // expected value = 600
    }
}
