package com.paypilot.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a bill with its details.
 */
public class Bill {
    private int userId;
    private int billId;
    private String billName;
    private String category;
    private LocalDate dueDate;
    private double amount;
    private boolean isRecurring;

    /**
     * Constructs a new Bill object.
     *
     * @param userId      The ID of the user associated with the bill.
     * @param billId      The unique ID of the bill.
     * @param billName    The name of the bill (e.g., "Electricity Bill").
     * @param category    The category of the bill (e.g., "Utilities").
     * @param dueDate     The date the bill is due.
     * @param amount      The amount of the bill.
     * @param isRecurring A boolean indicating if the bill is recurring.
     */
    public Bill(int userId, int billId, String billName, String category, LocalDate dueDate, double amount, boolean isRecurring) {
        this.userId = userId;
        this.billId = billId;
        this.billName = billName;
        this.category = category;
        this.dueDate = dueDate;
        this.amount = amount;
        this.isRecurring = isRecurring;
    }

    // --- Getters ---

    public int getUserId() {
        return userId;
    }

    public int getBillId() {
        return billId;
    }

    public String getBillName() {
        return billName;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    // --- Setters ---

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    /**
     * Displays the bill's details to the console.
     */
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Bill{" +
                "userId=" + userId +
                ", billId=" + billId +
                ", billName='" + billName + '\'' +
                ", category='" + category + '\'' +
                ", dueDate=" + dueDate +
                ", amount=" + amount +
                ", isRecurring=" + isRecurring +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return userId == bill.userId &&
                billId == bill.billId &&
                Double.compare(bill.amount, amount) == 0 &&
                isRecurring == bill.isRecurring &&
                Objects.equals(billName, bill.billName) &&
                Objects.equals(category, bill.category) &&
                Objects.equals(dueDate, bill.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, billId, billName, category, dueDate, amount, isRecurring);
    }
}
