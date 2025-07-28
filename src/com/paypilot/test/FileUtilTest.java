package com.paypilot.test;

import model.Payment;
import util.FileUtil;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilTest {

    @Test
    public void testWriteAndReadObject() throws Exception {
        Payment payment = new Payment("BILL123", 500.0);

        // Create a temporary file
        File tempFile = File.createTempFile("paymentTest", ".tmp");
        tempFile.deleteOnExit();

        // Write to file
        FileUtil.writeToFile(payment, tempFile);

        // Read from file
        Object readObj = FileUtil.readFromFile(tempFile);

        // Check if original and read objects are equal
        assertEquals(payment, readObj);
    }
}
