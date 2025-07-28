package com.paypilot.test;

import com.paypilot.model.Payment;
import com.paypilot.util.FileUtil;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


public class FileUtilTest {

    @Test
    public void testWriteAndReadPayment() throws Exception {
        // Create a payment object with updated structure
        Payment payment = new Payment(
                1,              // paymentId
                101,            // billId
                2500.50,        // amountPaid
                LocalDate.of(2025, 7, 28), // paymentDate
                "Credit Card"   // mode
        );

        // Create a temporary file
        File tempFile = File.createTempFile("paymentTest", ".tmp");
        tempFile.deleteOnExit();

        // Write the payment object to the file
        FileUtil.writeToFile(payment, tempFile);

        // Read the object back from the file
        Object readObj = FileUtil.readFromFile(tempFile);

        // Validate if the read object is equal to the original
        assertEquals(payment, readObj);
    }
}

