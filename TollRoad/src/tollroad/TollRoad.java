/*
 * TollRoad.java
 * This class represents a single road with a toll road system.
 * @author Natalie Ayuba
 */

package tollroad;

import java.util.ArrayList;
import java.util.Collections;

public class TollRoad {

    private final ArrayList<CustomerAccount> customerList;
    private int moneyMade;

    public TollRoad() {
        this.customerList = new ArrayList<>();
        this.moneyMade = 0;
    }

    public String getCustomers() {
        StringBuilder str = new StringBuilder();
        Collections.sort(customerList);
        for (CustomerAccount acc : customerList) {
           str.append(acc);
           if(!acc.equals(customerList.get(customerList.size()-1))) {
               str.append("\n");
           }
        }
        return str.toString();
    }

    public int getMoneyMade() {
        return moneyMade;
    }

    public void addCustomer(CustomerAccount acc) {
        customerList.add(acc);
    }

    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException {
        for (CustomerAccount acc : customerList) {
            if (acc.getCustomerVehicle().getRegNum().equals(regNum)) {
                return acc;
            }
        }
        throw new CustomerNotFoundException("CustomerAccount does not exist");
    }

    public void chargeCustomer(String registrationNumber) throws CustomerNotFoundException,
            InsufficientAccountBalanceException {
        moneyMade += this.findCustomer(registrationNumber).makeTrip();
    }

    @Override
    public String toString() {
        return customerList.size() + " customers, " + "money made = " + moneyMade;
    }

    //--------------------------------------------------------------------------
    // Test harness
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) throws CustomerNotFoundException, InsufficientAccountBalanceException {
        Car c = new Car("ND18 TWL", "Ford", 5);
        Truck t = new Truck("BDSG 4JH", "Ford", 0);
        CustomerAccount acc1 = new CustomerAccount("Jane", "Doe", c, 200);
        CustomerAccount acc2 = new CustomerAccount("John", "Doe", t, 50000);
        acc2.activateStaffDiscount();
        TollRoad tr = new TollRoad();
        tr.addCustomer(acc1);
        tr.addCustomer(acc2);
        System.out.println("Customers: " + "\n" + tr.getCustomers());
        System.out.println("Money made (pence): " + tr.getMoneyMade());
        
        System.out.println();
        
        System.out.println("Found customer: "  + tr.findCustomer("ND18 TWL")); // expected outcome = Jane Doe's account
        try {
            System.out.println("Found customer: " + tr.findCustomer("eebrhj"));
                // expected outcome = CustomerNotFoundException, customer = null
        } catch(CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        
        tr.chargeCustomer("BDSG 4JH"); // expected outcome = charges account 625
        System.out.println("**Customer charged.");
        try {
            tr.chargeCustomer("ND18 TWL"); // expected outcome = InsufficientAccountBalanceException
        } catch(InsufficientAccountBalanceException error) {
            System.out.println(error.getMessage());
        }
        try {
            tr.chargeCustomer("eebrhj"); // expected outcome = CustomerNotFoundException
        } catch(CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println();
        System.out.println("Money made (pence): " + tr.getMoneyMade());
    }
}
