package com.paypilot.model;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable {
    private String billId;
    private double amountPaid;

    public Payment(String billId, double amountPaid) {
        this.billId = billId;
        this.amountPaid = amountPaid;
    }

    // Getters & Setters
    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amountPaid, amountPaid) == 0 &&
                Objects.equals(billId, payment.billId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, amountPaid);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "billId='" + billId + '\'' +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
