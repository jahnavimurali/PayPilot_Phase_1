package com.paypilot.main;


import com.paypilot.model.*;
import com.paypilot.service.*;
import com.paypilot.util.*;
import com.paypilot.exception.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Authentication authService = new Authentication();
    private static final BillFunctions billFunctions = new BillFunctions();
    private static final PaymentService paymentService = new PaymentService();
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        // Populate with mock data
        List<Bill> mockBills = MockDataGenerator.generateBills();
        for (Bill bill : mockBills) {
            billFunctions.addBill(bill);
        }

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n--- Welcome to PayPilot ---");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Please select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                System.out.println("Thank you for using PayPilot. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void login() {
        System.out.print("Enter User ID: ");
        int userId = getIntInput();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authService.authenticateUserPassword(userId, password)) {
            // Find the user object. In a real app, you'd fetch this from a user service.
            for (User user : authService.users) {
                if (user.getUserId() == userId) {
                    currentUser = user;
                    break;
                }
            }
            System.out.println("\nLogin successful! Welcome, " + currentUser.getName() + ".");
        } else {
            System.out.println("Login failed. Invalid User ID or Password.");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View All My Bills");
        System.out.println("2. View Overdue Bills");
        System.out.println("3. View Bills by Category");
        System.out.println("4. Pay a Bill");
        System.out.println("5. Export My Bills");
        System.out.println("6. Logout");
        System.out.print("Please select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                viewAllBills();
                break;
            case 2:
                viewOverdueBills();
                break;
            case 3:
                viewBillsByCategory();
                break;
            case 4:
                payBill();
                break;
            case 5:
                exportBills();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void viewAllBills() {
        List<Bill> userBills = billFunctions.findAllBillsByUser(currentUser.getUserId());
        if (userBills.isEmpty()) {
            System.out.println("You have no bills.");
            return;
        }
        System.out.println("\n--- Your Bills ---");
        userBills.forEach(System.out::println);
    }

    private static void viewOverdueBills() {
        List<Bill> userBills = billFunctions.findAllBillsByUser(currentUser.getUserId());
        List<Bill> overdueBills = BillFunctions.getOverDueBills(userBills);
        if (overdueBills.isEmpty()) {
            System.out.println("You have no overdue bills. Great job!");
            return;
        }
        System.out.println("\n--- Your Overdue Bills ---");
        overdueBills.forEach(System.out::println);
    }

    private static void viewBillsByCategory() {
        List<Bill> userBills = billFunctions.findAllBillsByUser(currentUser.getUserId());
        Map<String, List<Bill>> groupedBills = BillFunctions.groupBillsByCategory(userBills);
        if (groupedBills.isEmpty()) {
            System.out.println("You have no bills to categorize.");
            return;
        }
        System.out.println("\n--- Bills by Category ---");
        groupedBills.forEach((category, bills) -> {
            System.out.println("\nCategory: " + category);
            bills.forEach(System.out::println);
        });
    }

    private static void payBill() {
        System.out.print("Enter the Bill ID you want to pay: ");
        int billId = getIntInput();
        List<Bill> userBills = billFunctions.findAllBillsByUser(currentUser.getUserId());
        Bill billToPay = BillFunctions.getABillById(userBills, billId);

        if (billToPay == null) {
            System.out.println("Bill with ID " + billId + " not found in your account.");
            return;
        }

        // In a real app, you'd remove or mark the bill as paid.
        // For this simulation, we'll just confirm payment.
        System.out.println("Payment for bill '" + billToPay.getBillName() + "' was successful.");
    }

    private static void exportBills() {
        List<Bill> userBills = billFunctions.findAllBillsByUser(currentUser.getUserId());
        String filePath = "exported_bills_" + currentUser.getUserId() + ".txt";
        BillFunctions.exportBillsToTextFile(userBills, filePath);
    }

    private static void logout() {
        System.out.println("Logging out. See you next time, " + currentUser.getName() + "!");
        currentUser = null;
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // discard non-integer input
        }
        return scanner.nextInt();
    }
}