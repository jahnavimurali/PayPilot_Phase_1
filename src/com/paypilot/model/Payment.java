package com.paypilot.model;

import java.util.Date;
import java.util.Objects;



public class Payment {
	
	private int paymentId;
    private int billId;
    private double amountPaid;
    private Date paymentDate;
    private String mode;
    
    public Payment(int paymentId, int billId, double amountPaid, Date paymentDate, String mode) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.mode = mode;
    }
    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentId == payment.paymentId &&
                billId == payment.billId &&
                Double.compare(payment.amountPaid, amountPaid) == 0 &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(mode, payment.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, billId, amountPaid, paymentDate, mode);
    }

}

