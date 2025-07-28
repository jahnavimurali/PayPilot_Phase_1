package com.paypilot.model;

import java.time.LocalDate;
import java.util.Objects;
import com.paypilot.util.DateUtil;

public class Payment {

    private int paymentId;
    private int billId;
    private double amountPaid;
    private LocalDate paymentDate;
    private String mode;

    // Constructor 
    public Payment(int paymentId, int billId, double amountPaid, LocalDate paymentDate, String mode) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.amountPaid = validateAmount(amountPaid);
        this.paymentDate = paymentDate;
        this.mode = mode;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = validateAmount(amountPaid);
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    // Amount validation
    private double validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        return amount;
    }

    // Display method
    public void display() {
        System.out.println("Payment ID  : " + paymentId);
        System.out.println("Bill ID     : " + billId);
        System.out.println("Amount Paid : ₹" + amountPaid);
        System.out.println("Payment Date: " + (paymentDate != null ? DateUtil.format(paymentDate) : "N/A"));
        System.out.println("Mode        : " + mode);

        LocalDate today = LocalDate.now();
        if (paymentDate != null) {
            if (paymentDate.isAfter(today)) {
                System.out.println("Status      : Scheduled");
            } else if (paymentDate.isEqual(today)) {
                System.out.println("Status      : Processed Today");
            } else {
                System.out.println("Status      : Completed");
            }
        }
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return paymentId == payment.paymentId &&
                billId == payment.billId &&
                Double.compare(payment.amountPaid, amountPaid) == 0 &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(mode, payment.mode);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(paymentId, billId, amountPaid, paymentDate, mode);
    }

    // toString
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", billId=" + billId +
                ", amountPaid=" + amountPaid +
                ", paymentDate=" + paymentDate +
                ", mode='" + mode + '\'' +
                '}';
    }
}
