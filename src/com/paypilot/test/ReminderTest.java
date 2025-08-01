package com.paypilot.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import com.paypilot.model.Reminder;
import org.junit.jupiter.api.Test;

public class ReminderTest {

    @Test
    public void testReminderExactly2DaysBefore() {
        System.out.println("Running: testReminderExactly2DaysBefore");

        LocalDate dueDate = LocalDate.of(2025, 8, 10);
        LocalDate currentDate = LocalDate.of(2025, 8, 8);

        boolean result = Reminder.shouldSendReminder(dueDate, currentDate);
        System.out.println("Expected: true, Actual: " + result);

        assertTrue(result, "Reminder should be sent exactly 2 days before due date");
    }

    @Test
    public void testReminder1DayBefore() {
        System.out.println("Running: testReminder1DayBefore");

        LocalDate dueDate = LocalDate.of(2025, 8, 10);
        LocalDate currentDate = LocalDate.of(2025, 8, 9);

        boolean result = Reminder.shouldSendReminder(dueDate, currentDate);
        System.out.println("Expected: false, Actual: " + result);

        assertFalse(result, "Reminder should NOT be sent 1 day before");
    }

    @Test
    public void testReminder3DaysBefore() {
        System.out.println("Running: testReminder3DaysBefore");

        LocalDate dueDate = LocalDate.of(2025, 8, 10);
        LocalDate currentDate = LocalDate.of(2025, 8, 7);

        boolean result = Reminder.shouldSendReminder(dueDate, currentDate);
        System.out.println("Expected: false, Actual: " + result);

        assertFalse(result, "Reminder should NOT be sent 3 days before");
    }

    @Test
    public void testReminderOnDueDate() {
        System.out.println("Running: testReminderOnDueDate");

        LocalDate dueDate = LocalDate.of(2025, 8, 10);
        LocalDate currentDate = LocalDate.of(2025, 8, 10);

        boolean result = Reminder.shouldSendReminder(dueDate, currentDate);
        System.out.println("Expected: false, Actual: " + result);

        assertFalse(result, "Reminder should NOT be sent on the due date itself");
    }
    

}

