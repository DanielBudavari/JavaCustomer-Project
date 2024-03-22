// Assignment #: ASU CSE205 Spring 2023 #10
// Name: Daniel Budavari
// StudentID: 1224612207
// Lecture: TU THUR 1:30
// Description: LoanOfficer class represents a bank loan officer
//              that accept/handle and release customers.

public class LoanOfficer
{
   private int officerID;
   private Customer currentCustomer;

   //**************************************************
   //Constructor to initialize member variables
   //Initially no patient is assigned
   public LoanOfficer(int id)
   {
      this.officerID = id;
      currentCustomer = null;
   }

   //******************************************
   //an accessor method for the officer's ID
   public int getID()
   {
      return officerID;
   }
   
   public Customer getCurrentCust()
   {
	   return currentCustomer;
   }

   //****************************************************************
   //method to determine if a loanOfficer currently has a customer by
   //checking the currentCustomer variable
   public boolean hasCustomer()
   {
	   if (this.currentCustomer == null) {
		   return false;
	   }
	   else {
		   return true;
	   }
   }

   //************************************************************************
   //assign customer1 to currentCustomer if the officer does not have customer
   public boolean assignCustomer(Customer customer1)
   {
	   if (this.hasCustomer() == false) {
		   
		   this.currentCustomer = customer1;
		   return true;
	   } 
	   else {
		   return false;
	   }
   }

   //*********************************************
   public Customer handleCustomer()
   {
     if (this.hasCustomer() == true) {
    	 Customer temp = currentCustomer;
    	 currentCustomer = null;
    	 return temp;
     } 
     else {
    	 return null;
     }
   }

   //********************************************
   //toString method returns a string containing
   //the information of a loanOfficer
   public String toString()
   {
      String result = "\nOfficer ID: " + officerID;

      if (currentCustomer == null)
         result += " does not have any cutomers\n";
      else
         result += " is serving customer with id " + currentCustomer.getCustID() + "\n";

      return result;
   }
}