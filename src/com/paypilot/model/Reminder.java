package com.paypilot.model;

import java.time.LocalDate;
import java.util.Objects;


public class Reminder {
    private String reminderId;
    private String billId;
    private LocalDate reminderDate;
    private String message;

    public Reminder(String reminderId, String billId, LocalDate reminderDate, String message) {
        this.reminderId = reminderId;
        this.billId = billId;
        this.reminderDate = reminderDate;
        this.message = message;
    }

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId=" + reminderId +
                ", billId=" + billId +
                ", reminderDate=" + reminderDate +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return reminderId == reminder.reminderId &&
                billId == reminder.billId &&
                Objects.equals(reminderDate, reminder.reminderDate) &&
                Objects.equals(message, reminder.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminderId, billId, reminderDate, message);
    }

    public static boolean shouldSendReminder(LocalDate billDueDate, LocalDate today) {
        return today.equals(billDueDate.minusDays(2));
    }
    
}

