// Assignment #: ASU CSE205 Spring 2023 #10
// Name: Daniel Budavari
// StudentID: 1224612207
// Lecture: TU THUR 1:30
// Description: This program manages customer queues, assigns customer to loan officer,
//              process and release them, etc.

import java.util.LinkedList;

public class CustomerManagement
{
   private LinkedList<Customer> LEQueue;
   private LinkedList<Customer> MEQueue;
   private LinkedList<Customer> SEQueue;

   private LinkedList<Customer> checkoutQueue;

   private LoanOfficer[] officerList;

   //**********************************************
   //Constructor
   public CustomerManagement(int numOfLoanOfficers)
   {
      LEQueue = new LinkedList<Customer>();
      MEQueue = new LinkedList<Customer>();
      SEQueue = new LinkedList<Customer>();
      checkoutQueue = new LinkedList<Customer>();

      //creating LoanOfficer objects
      officerList = new LoanOfficer[numOfLoanOfficers];
      for (int i=0; i< officerList.length; i++)
      {
         officerList[i] = new LoanOfficer(i);
      }
   }

   //*******************************************************************
   //add a customer to the corresponding queue based on its category
   //return true if added to a queue successfully; otherwise return false
   boolean addCustomer(int id, String category)
   {
      if (category.equals("LE")) {
    	  Customer customerLE = new Customer(id, category);
    	  LEQueue.add(customerLE);
    	  return true;
    	  
      } else if (category.equals("ME")) {
    	  Customer customerME = new Customer(id, category);
    	  MEQueue.add(customerME);
    	  return true;
    	  
      } else if (category.equals("SE")){
    	  Customer customerSE = new Customer(id, category);
    	  SEQueue.add(customerSE);
    	  return true;
      } else {
    	  return false;
      }
   }

   //*******************************************************************
   //assign a customer to a loan officer with large enterprise (LE) queues
   //going first; return null if there are no customers in the queues or if
   //there are no loan officer are available
   Customer assignCustomerToLoanOfficer()
   {
	   Customer assigned = null;
	   if(officerList.length == 0) {
		  return null;}
	
	   if (!LEQueue.isEmpty()) {
			   for (int i = 0; i < officerList.length; i++) {
				   
				   if(officerList[i].hasCustomer() == false) {
					   assigned = LEQueue.getFirst();
					   LEQueue.removeFirst();
					   officerList[i].assignCustomer(assigned);
					   return assigned;
				   } 
			   }
	   }
	  
	   if (!MEQueue.isEmpty()) {
			   for (int i = 0; i < officerList.length; i++) {
				   
				   if(officerList[i].hasCustomer() == false) {
					   assigned = MEQueue.getFirst();
					   MEQueue.removeFirst();
					   officerList[i].assignCustomer(assigned);
					   return assigned;
				   } 
			   }
		}
		
	    if (!SEQueue.isEmpty()) {
			   for (int i = 0; i < officerList.length; i++) {
				   
				   if(officerList[i].hasCustomer() == false) {
					   assigned = SEQueue.getFirst();
					   SEQueue.removeFirst();
					   officerList[i].assignCustomer(assigned);
					   return assigned;
				   } 
			    }
	    }
	    return null;
	}

   //***************************************************************
   //check if officer with the officerID has a customer, and release
   //that customer if they do. Then add that customer to checkout queue
   //and return the Customer object; otherwise return null
   Customer releaseCustomerFromOfficer(int officerID)
   {
	   int targetOfficer = 0;
	   while (officerList[targetOfficer].getID() != officerID) {
		   targetOfficer++;
	   }
	   
	   if(officerList[targetOfficer].hasCustomer() == true) {
		   Customer released = officerList[targetOfficer].getCurrentCust();
		   officerList[targetOfficer].handleCustomer();
		   checkoutQueue.add(released);
		   return released;
	   } else {
		   return null;
	   }
   }

   //**************************************************************
   //remove the first Customer from the checkoutQueue and return it;
   //otherwise the method return null.
   public Customer checkoutCustomer()
   {
	   if(!checkoutQueue.isEmpty()) {
	   Customer removedCust = checkoutQueue.getFirst();
	   checkoutQueue.removeFirst();
	   return removedCust;
	   } else {
		   return null;
	   }
   }

   //************************************************
   //The printQueue prints out the content
   //of the queues and the array of loan officers
   public void printQueues()
   {
      System.out.print("\nLarge Enterprise Queue:\n" + LEQueue.toString() + "\n");
      System.out.print("\nMedium Enterprise Queue:\n" + MEQueue.toString() + "\n");
      System.out.print("\nSmall Enterprise Queue:\n" + SEQueue.toString() + "\n\n");
      for (int i = 0; i < officerList.length; i++)
      {
         System.out.print(officerList[i].toString());
      }
      System.out.print("\nCheckout Customer Queue:\n" + checkoutQueue.toString() + "\n\n");
   }  
}