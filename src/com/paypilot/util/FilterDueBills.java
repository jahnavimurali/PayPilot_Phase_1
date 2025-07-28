package com.paypilot.util;

import com.paypilot.model.Bill;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDueBills {

    public static List<Bill> findBillsDueInNextSevenDays(List<Bill> bills) {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plusDays(7);

        return bills.stream()
                .filter(bill -> {
                    LocalDate dueDate = bill.getDueDate();
                    return (dueDate != null && !dueDate.isBefore(today) && !dueDate.isAfter(nextWeek));
                })
                .collect(Collectors.toList());
}
}