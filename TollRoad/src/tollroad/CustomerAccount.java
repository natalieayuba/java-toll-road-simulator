/*
 * CustomerAccount.java
 * This class represents a CustomerAccount object which contains information about the customer including their name,
 * account balance, vehicle, and toll road discount type (if any).
 * @author Natalie Ayuba
 */

package tollroad;

public class CustomerAccount implements Comparable<CustomerAccount> {

    private enum DiscountType {
        NONE, STAFF, FRIENDS_AND_FAMILY
    }

    private final String firstName;
    private final String lastName;
    private final Vehicle customerVehicle;
    private int accountBalance; // prepaid balance available for use on the toll road
    private DiscountType discountType;

    public CustomerAccount(String firstName, String lastName, Vehicle customerVehicle, int accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerVehicle = customerVehicle;
        this.accountBalance = accountBalance;
        this.discountType = DiscountType.NONE;
    }

    public void activateStaffDiscount() {
        discountType = DiscountType.STAFF;
    }

    public void activateFriendsAndFamilyDiscount() {
       if(discountType.equals(DiscountType.STAFF)) {
           System.out.println("Account already has Staff discount. Cannot apply Friends and Family discount.");
       } else {
           discountType = DiscountType.FRIENDS_AND_FAMILY;
       }
    }

    public void deactivateDiscount() {
        discountType = DiscountType.NONE;
    }

    public void addFunds(int amount) {
        accountBalance += amount;
    }

    public int makeTrip() throws InsufficientAccountBalanceException {
        int cost = customerVehicle.calculateBasicTripCost();
        if(discountType == DiscountType.STAFF) {
            cost = (int) (cost * 0.5);
        } else if(discountType == DiscountType.FRIENDS_AND_FAMILY) {
            cost = (int) (cost * 0.9);
        } if((accountBalance - cost) <= 0) {
            throw new InsufficientAccountBalanceException("Insufficient funds");
        }
        accountBalance -= cost;
        return cost;
    }

    @Override
    public int compareTo(CustomerAccount otherAccount) {
        return this.customerVehicle.getRegNum().compareTo(otherAccount.customerVehicle.getRegNum());
    }
    
    // Accessor methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Vehicle getCustomerVehicle() {
        return customerVehicle;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public String getDiscountType() {
        return this.discountType.name();
    }
    
    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + customerVehicle + ", " + accountBalance + ", " + discountType;
    }
    
    //--------------------------------------------------------------------------
    // Test harness
    //--------------------------------------------------------------------------
    
    public static void main(String[] args) throws InsufficientAccountBalanceException {
        Van v = new Van("TE56 XDS", "Citroen", 500);
        CustomerAccount c1 = new CustomerAccount("Jane", "Doe", v, 200000);
        System.out.println(c1);
        // Testing accessors
        System.out.println("First name: " + c1.getFirstName());
        System.out.println("Last name: " + c1.getLastName());
        System.out.println("Vehicle: " + c1.getCustomerVehicle());
        System.out.println("Account Balance: " + c1.getAccountBalance());
        System.out.println("Discount Type: " + c1.getDiscountType());
        
        System.out.println();
        
        // Testing mutators
        c1.activateStaffDiscount();
        System.out.println("**Staff Discount Applied.");
        System.out.println("New Discount Type: " + c1.getDiscountType()); // expected outcome = STAFF
        c1.activateFriendsAndFamilyDiscount();
        System.out.println("New Discount Type: " + c1.getDiscountType()); // expected outcome = (discountType should remain as) STAFF
        c1.deactivateDiscount();
        System.out.println("**Discount Deactivated.");
        System.out.println("New Discount Type: " + c1.getDiscountType()); // expected outcome = NONE
        c1.activateFriendsAndFamilyDiscount();
        System.out.println("**Family and Friends Discount Applied.");
        System.out.println("New Discount Type: " + c1.getDiscountType()); // expected outcome = FRIENDS_AND_FAMILY
        
        System.out.println();

        // Testing addFunds() and makeTrip() methods
        c1.addFunds(2500);
        System.out.println("**2500 added to account.");
        System.out.println("New account balance: " + c1.getAccountBalance()); // expected outcome = 202500
        System.out.println("Trip cost: " + c1.makeTrip()); // expected value = 450
        System.out.println("Balance after trip: " + c1.getAccountBalance()); // expected outcome = 202050
        
        System.out.println();

        // Testing InsufficientAccountBalanceException
        CustomerAccount c2 = new CustomerAccount("John", "Doe", v, 200);
        try {
            System.out.println("Trip cost: " + c2.makeTrip());
        } catch(InsufficientAccountBalanceException e) {
            System.out.println(e.getMessage()); // expected InsufficientAccountBalanceException throwm
        }
    }
}