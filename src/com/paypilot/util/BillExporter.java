package com.paypilot.util;

import com.paypilot.model.Bill;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class to export bills to a text file.
 */
public class BillExporter {

    /**
     * Exports all bills to the specified text file.
     *
     * @param bills    The list of bills to export.
     * @param filePath The file path where the bills will be written.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void exportBillsToFile(List<Bill> bills, String filePath) throws IOException {
        if (bills == null || bills.isEmpty()) {
            throw new IllegalArgumentException("No bills to export.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Bill bill : bills) {
                writer.write(bill.toString());
                writer.newLine();
            }
        }
    }
}
//Kanishk
