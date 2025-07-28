package com.paypilot.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    // Formatter to handle "dd-MM-yyyy" date format
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Converts a string like "28-07-2025" to LocalDate
    public static LocalDate parse(String dateStr) {
        if (dateStr == null) {
            throw new IllegalArgumentException("Input date string cannot be null");
        }
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected dd-MM-yyyy", e);
        }
    }

    // Converts a LocalDate to a string like "28-07-2025"
    public static String format(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Input date cannot be null");
        }
        return date.format(FORMATTER);
    }
}
