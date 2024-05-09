package com.hexaware.date.util;

import java.text.SimpleDateFormat;

public class DateUtil {
    public static java.sql.Date convertStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM_dd");
        try {
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new java.sql.Date(parsedDate.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(java.sql.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM_dd");
        return dateFormat.format(date);
    }

    public static java.sql.Date getCurrentDate() {
        long currentTimeMillis = System.currentTimeMillis();

        // Create a java.sql.Date object from current time
        java.sql.Date currentDate = new java.sql.Date(currentTimeMillis);

        // return the current date
        return currentDate;
    }
}
