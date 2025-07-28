package com.paypilot.service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import com.paypilot.model.Bill;
import com.paypilot.model.User; 

public class BillFunctions {

    private List<Bill> billList; // Variable to store all the bills

    public BillFunctions() {
        this.billList = new ArrayList<>();
    }

    // Method to populate bills
    public void addBill(Bill bill) {
        billList.add(bill);
    }

    // Method to find and return all bills
    public List<Bill> findAllBills() {
        return new ArrayList<>(billList); //Returning a copy of Bill Lists to protect internal list
    }

    // Method to filter and return bills based on user
    public List<Bill> findAllBillsByUser(int userId) {
        return billList.stream()
            .filter(bill -> bill.getUserId() == userId)
            .collect(Collectors.toList());
    }
// Method to get Total Due Bills of a Specific User
    public double getTotalDueBill(User user) {
        List<Bill> userSpecificBills = findAllBillsByUser(user.getUserId());
        double totalDue = 0;
        for (Bill bill : userSpecificBills) {
            if (bill.getDueDate().isBefore(LocalDate.now())) {
                totalDue += bill.getAmount();
            }
        }
        return totalDue;
    }

  // Function to check if a bill is recurring 
  public static boolean isBillRecurring(Bill bill) {
    return bill != null && bill.isRecurring();
  }

  // Function to avoid generating a recurring bill if it already exists
  public static boolean hasRecurringBillForNextMonth(Bill originalBill, List<Bill> allBills) {
	    LocalDate nextDueDate = originalBill.getDueDate().plusMonths(1);

	    return allBills.stream().anyMatch(existing ->
	        existing.getUserId() == originalBill.getUserId() &&
	        existing.getBillName().equalsIgnoreCase(originalBill.getBillName()) &&
	        existing.getCategory().equalsIgnoreCase(originalBill.getCategory()) &&
	        existing.getDueDate().equals(nextDueDate)
	    );
	}

  // Function to check if a bill is recurring and autogenerating next months's bill
  public static List<Bill> checkAndGenerateRecurringBills(List<Bill> bills) 
  {
        List<Bill> newBills = new ArrayList<>();
        Set<String> existingIds = new HashSet<>();
        LocalDate currentDate = LocalDate.now();

        //Adding existing IDs to the set
        for (Bill bill : bills) 
        {
            existingIds.add(bill.getBillId());
        }

        for (Bill bill : bills) 
        {
            if (bill.isRecurring() && bill.getDueDate().isBefore(currentDate)) 
            {
                String newId;
                // Generating a unique ID that doesn't clash
                do 
                {
                    newId = UUID.randomUUID().toString();
                } while (existingIds.contains(newId));
                
                existingIds.add(newId);

                // Generating Date for the next month's bill
                LocalDate nextDueDate = bill.getDueDate().plusMonths(1);
                
                 //Checking if there is a bill already generated for next month
                if (!hasRecurringBillForNextMonth(bill, bills))
                {
                	// Creating bill for the next month 
                    Bill nextMonthBill = new Bill(
                    	bill.getUserId(),
                        newId,
                        bill.getBillName(),
                        bill.getCategory(),
                        nextDueDate,
                        bill.getAmount(),
                        true
                    );
                    
                    newBills.add(nextMonthBill);
                }
            }
        }

        return newBills;
    }
}
