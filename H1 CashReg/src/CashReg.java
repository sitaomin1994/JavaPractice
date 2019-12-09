/**
   A cash register totals up sales and computes change due.

   Version for CS 455 lab 3.  Modified from version from Big Java, 6th
   ed.

   Changes [made by CMB]:

     * This version of the class is called CashReg (instead of CashRegister)
     * Added getTotal() accessor function.
     *  Made constants private.

   Ex:
   CashReg register = new CashReg();
   register.recordPurchase(0.59);  // ring something up
   register.recordPurchase(1.99);  // ring up another item
   register.recordPurchase(5.0);   // ring up a third item
   double tot = register.getTotal();    // total of purchases so far: 7.58
   register.receivePayment(10,0,0,0,0);  // customer pays with a 10
   int change = register.giveChange();  // compute change owed: 2.42
                                        // and zeroes out register

   register.recordPurchase(1.0);  // now we start ringing up someone else . . .

*/

import java.lang.Math;

public class CashReg
{

   private double purchase;
   private double payment;

   /**
      Constructs a cash register with no money in it.
   */
   public CashReg()
   {
      purchase = 0;
      payment = 0;
   }

   /**
      Records the purchase price of an item.
      @param amount the price of the purchased item
   */
   public void recordPurchase(double amount)
   {
      purchase = purchase + amount;
   }
   
   /**
      Gets total of all purchases made.
   */
    public double getTotal() {
       return purchase;
    }; 

   /**
      Enters the payment received from the customer.
      @param dollars the number of dollars in the payment
      @param quarters the number of quarters in the payment
      @param dimes the number of dimes in the payment
      @param nickels the number of nickels in the payment
      @param pennies the number of pennies in the payment
   */
   public void receivePayment(Change money)
   {
      payment  = (double)money.totalValue()/100;
   }
   
   /**
      Computes the change due and resets the machine for the next customer.
      @return the change due to the customer
   */
   public Change giveChange()
   {
       int dollars = 0;
       int quarters = 0;
       int dimes = 0;
       int nickels = 0;
       int pennies = 0;
       double change_temp =  (double)(payment*100-Math.round(purchase*100))/100;
       payment = 0;
       purchase = 0;
       int change_in_cents = (int)(change_temp*100);
       dollars = change_in_cents/100;
       change_in_cents -= dollars*100;
       
       quarters = change_in_cents/25;
       change_in_cents -= quarters*25;
       
       dimes = change_in_cents/10;
       change_in_cents -= dimes*10;
       
       nickels = change_in_cents/5;
       change_in_cents -= nickels*5;
       
       pennies = change_in_cents/1;
       change_in_cents -= pennies*1;
       
       Change change = new Change(dollars,quarters,dimes,nickels,pennies);
       	  
	   return change;
   }
}
