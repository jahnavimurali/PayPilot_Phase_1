package com.paypilot.test;
import org.junit.jupiter.api.Test;
import com.paypilot.util.DateUtil;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
public class DateUtilTest {

    //Valid date input should return correct LocalDate
    @Test
    void testParseValidDate() {
        String validDate = "28-07-2025";
        LocalDate expected = LocalDate.of(2025, 7, 28);

        LocalDate result = DateUtil.parse(validDate);
        assertEquals(expected, result, "Parsed date should match expected LocalDate");
    }

    //Invalid format (e.g. wrong order "yyyy-MM-dd") should throw exception
    @Test
    void testParseInvalidFormatThrowsException() {
        String invalidDate = "2025-07-28";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateUtil.parse(invalidDate);
        });

        assertTrue(exception.getMessage().contains("Invalid date format"), "Should contain 'Invalid date format'");
    }

    //Invalid date string like "invalid-date" should throw exception
    @Test
    void testParseGarbageDateThrowsException() {
        String garbage = "invalid-date";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateUtil.parse(garbage);
        });

        assertTrue(exception.getMessage().contains("Invalid date format"), "Should contain 'Invalid date format'");
    }

    //Null string should throw exception with specific message
    @Test
    void testParseNullDateThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateUtil.parse(null);
        });

        assertEquals("Input date string cannot be null", exception.getMessage());
    }

    //Null LocalDate in format() should throw exception
    @Test
    void testFormatNullDateThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            DateUtil.format(null);
        });

        assertEquals("Input date cannot be null", exception.getMessage());
    }

    //Valid LocalDate should return correct formatted string
    @Test
    void testFormatValidDate() {
        LocalDate date = LocalDate.of(2025, 7, 28);
        String expected = "28-07-2025";

        String result = DateUtil.format(date);
        assertEquals(expected, result, "Formatted string should match expected pattern");
    }
}

