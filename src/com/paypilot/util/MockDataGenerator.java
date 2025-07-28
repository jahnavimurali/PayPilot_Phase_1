package com.paypilot.util;

import com.paypilot.model.Bill;
import com.paypilot.model.Payment;
import com.paypilot.model.User;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MockDataGenerator {
    private static final String[] BILL_NAMES = {
        "Water Bill", "Internet Bill", "Mobile Bill", "Gas Bill", "Electricity Bill"
    };
    private static final String[] CATEGORIES = {
        "Water", "Internet", "Mobile", "Gas", "Electricity"
    };
    private static final String[] PAYMENT_MODES = {
        "Credit Card", "NetBanking", "Cash on Delivery"
    };

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Hari", "hari@gmail.com", "9876543210", "pass123"));
        users.add(new User(2, "Vijay", "vijay@gmail.com", "8765432109", "bpass2"));
        users.add(new User(3, "Chandru", "chandru@gmail.com", "7654321098", "chaie1"));
        users.add(new User(4, "Neha", "neha@gmail.com", "6543210987", "huina123"));
        users.add(new User(5, "Cheera", "cheera@gmail.com", "8432109876", "huinn@123"));
        return users;
    }

public static List<Bill> generateBills() {
        List<Bill> bills = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (int i = 1; i <= 10; i++) {
            int userId = (i % 5) + 1;
            int index = (i - 1) % BILL_NAMES.length;
            LocalDate dueDate = today.minusDays(i);
            bills.add(new Bill(
                userId,
                i,
                BILL_NAMES[index],
                CATEGORIES[index],
                dueDate,
                100 + i * 15,
                i % 2 == 0
            ));
        }
        return bills;
    }

    public static List<Payment> generatePayments(List<Bill> bills) {
        List<Payment> payments = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i <= 5; i++) {
            Bill bill = bills.get(i - 1);
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_MONTH, -i);
            String paymentDate = sdf.format(cal.getTime());
            payments.add(new Payment(
                i,
                bill.getBillId(),
                bill.getAmount(),
                paymentDate,
                PAYMENT_MODES[i % PAYMENT_MODES.length]
            ));
        }
        return payments;
    }
}
