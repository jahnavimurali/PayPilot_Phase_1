package com.paypilot.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Payment implements Serializable {

    private int paymentId;
    private int billId;
    private double amountPaid;
    private LocalDate paymentDate;
    private String mode;

    // Constructor
    public Payment(int paymentId, int billId, double amountPaid, LocalDate paymentDate, String mode) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.amountPaid = amountPaid;
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
        this.amountPaid = amountPaid;
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

    // equals method for JUnit and object comparison
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

    // hashCode for proper collection support
    @Override
    public int hashCode() {
        return Objects.hash(paymentId, billId, amountPaid, paymentDate, mode);
    }

    // toString for readable output
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
