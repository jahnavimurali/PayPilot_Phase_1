
package com.paypilot.util;
import java.util.*;
import com.paypilot.model.Bill;

public class BillSorter {

    public static List<Bill> SortedBillsByDueDate(List<Bill> bills) {
        List<Bill> sortedBills = new ArrayList<>(bills);  
        sortedBills.sort(Comparator.comparing(Bill::getDueDate)); 
        return sortedBills;
    }

}
// used Comparator interface to sort Bills by their due date
//Saloni
