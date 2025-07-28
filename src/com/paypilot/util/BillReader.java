package com.paypilot.util;

import com.paypilot.model.Bill;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillReader {

    public static List<Bill> readBillsFromCSV(String filename) throws IOException {
        List<Bill> bills = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("bills.csv"))) {
            String line = br.readLine(); // for reading header to skip it.

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1); //to keep trailing empty strings

                if(fields.length != 7) {
                    System.err.println("Warning: Skipping malformed line: " + line);
                    continue;
                }

                try {
                    int userId = Integer.parseInt(fields[0].trim());
                    int billId = Integer.parseInt(fields[1].trim());
                    String billName = fields[2].trim();
                    String category = fields[3].trim();
                    LocalDate dueDate = LocalDate.parse(fields[4].trim()); 
                    double amount = Double.parseDouble(fields[5].trim());
                    boolean isRecurring = Boolean.parseBoolean(fields[6].trim());

                    Bill bill = new Bill(userId, billId, billName, category, dueDate, amount, isRecurring);
                    bills.add(bill);
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    System.err.println("Cause: " + e.getMessage()); 
                }
            }
        }

        return bills;
    }

   
    public static void main(String[] args) {
        String csvPath = "bills.csv"; 

        try {
            List<Bill> bills = readBillsFromCSV(csvPath);
            System.out.println("Bills loaded: " + bills.size());
            for(Bill bill : bills) {
                System.out.println(bill);
            }
        } catch (IOException e) {
            System.err.println("Failed to read bills: " + e.getMessage());
        }
    }
}
