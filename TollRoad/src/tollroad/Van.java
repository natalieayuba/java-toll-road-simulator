/*
 * Van.java
 * This class represents a Van object which extends the Vehicle object, with a registration number, manufacturer, and
 * payload (amount of cargo in kg that a van can store).
 * @author: Natalie Ayuba
 */

package tollroad;

public class Van extends Vehicle {

    private final int payload; // amount of cargo (in kg) that a van can store

    public Van(String regNum, String manufacturer, int payload) {
        super(regNum, manufacturer);
        this.payload = payload;
    }

    // Accessor method
    public int getPayload() {
        return payload;
    }

    @Override
    public int calculateBasicTripCost() {
        if(payload <= 600) {
            return 500;
        } else if(payload <= 800) {
            return 750;
        } else {
            return 1000;
        }
    }
        
    @Override
    public String toString() {
        return this.getManufacturer() + ", " + this.getRegNum() + ", " + payload + "kg";
    }
    
    //--------------------------------------------------------------------------
    // Test harness
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) {
        Van v1 = new Van("TE56 XDS", "Citroen", 500);
        System.out.println("Van #1: " + v1);
        // Testing accessor methods
        System.out.println("Manufacturer: " + v1.getManufacturer());
        System.out.println("Registration number: " + v1.getRegNum());
        System.out.println("Payload (kg): " + v1.getPayload());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + v1.calculateBasicTripCost()); //expected value = 500

        System.out.println();

        Van v2 = new Van("RW69 DFS", "BMW", 725);
        System.out.println("Van #2: " + v2);
        // Testing accessor methods
        System.out.println("Manufacturer: " + v2.getManufacturer());
        System.out.println("Registration number: " + v2.getRegNum());
        System.out.println("Payload (kg): " + v2.getPayload());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + v2.calculateBasicTripCost()); // expected value = 750

        System.out.println();

        Van v3 = new Van("YH45 NAT", "Audi", 950);
        System.out.println("Van #3: " + v3);
        //Testing accessor methods
        System.out.println("Manufacturer: " + v3.getManufacturer());
        System.out.println("Registration number: " + v3.getRegNum());
        System.out.println("Payload (kg): " + v3.getPayload());
        // Testing calculateBasicTripCost() method
        System.out.println("Cost of trip (pence): " + v3.calculateBasicTripCost()); // expected value = 1000
    }
}
