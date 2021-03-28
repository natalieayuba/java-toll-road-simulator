/*
 * TollRoad.java
 * This class represents a toll road system.
 * @author Natalie Ayuba
 */

package tollroad;

import java.io.*;
import java.util.Scanner;

public class TollRoadMain {

    private static TollRoad initialiseTollRoadFromFile() throws FileNotFoundException {
        TollRoad tr = new TollRoad();
        Scanner scan = new Scanner(new File("customerData.txt"));
        scan.useDelimiter(",|#");

        while(scan.hasNext()) {
            String vehicleType = scan.next(); // Car, Van, or Truck
            String regNum = scan.next();
            String firstName = scan.next();
            String lastName = scan.next();
            String vehicleMake = scan.next();
            int vehicleInfo = scan.nextInt(); // numberOfSeats for Car, payload for Van, numTrailers for Truck
            int startingBalance = scan.nextInt();
            String discountType = scan.next();

            Vehicle vehicle = null;
            switch(vehicleType) {
                case "Van":
                    vehicle = new Van(regNum, vehicleMake, vehicleInfo);
                    break;
                case "Car":
                    vehicle = new Car(regNum, vehicleMake, vehicleInfo);
                    break;
                case "Truck":
                    vehicle = new Truck(regNum, vehicleMake, vehicleInfo);
                    break;
            }

            CustomerAccount acc = new CustomerAccount(firstName, lastName, vehicle, startingBalance);

            switch(discountType) {
                case "STAFF":
                    acc.activateStaffDiscount();
                    break;
                case "FRIENDS_AND_FAMILY":
                    acc.activateFriendsAndFamilyDiscount();
                    break;
            }
            tr.addCustomer(acc);
        }
        return tr;
    }

    private static void simulateFromFile(TollRoad road) throws FileNotFoundException {
        String transaction, instruction, registrationNumber;
        int amount;
        CustomerAccount acc;
        Scanner scan = new Scanner(new File("transactions.txt"));
        scan.useDelimiter("\\$");
        while (scan.hasNext()) {
            transaction = scan.next();
            String[] tokens = transaction.split(",");
            instruction = tokens[0];
            registrationNumber = tokens[1];
            switch (instruction) {
                case "addFunds":
                    try {
                        amount = Integer.parseInt(tokens[2]);
                        acc = road.findCustomer(registrationNumber);
                        acc.addFunds(amount);
                        System.out.println(registrationNumber + ": " + amount + " added successfully");
                    } catch(CustomerNotFoundException e) {
                        System.out.println(registrationNumber + ": " + instruction + " failed. " + e.getMessage());
                    }
                    break;
                case "makeTrip":
                    try {
                        road.chargeCustomer(registrationNumber);
                        System.out.println(registrationNumber + ": Trip completed successfully");
                    } catch(CustomerNotFoundException | InsufficientAccountBalanceException e) {
                        System.out.println(registrationNumber + ": " + instruction + " failed. " + e.getMessage());
                    }
                    break;
            }
        }
    }
   
    public static void main(String[] args) throws IOException {
        TollRoad tr = initialiseTollRoadFromFile();
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        simulateFromFile(tr);
        System.out.println("Total money made: " + tr.getMoneyMade());
    }
}
