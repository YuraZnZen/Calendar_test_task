package com.company.main;

import com.company.classes.CalendarPerMonth;
import com.company.interfaces.CalendarMonthFactory;
import com.company.interfaces.DefaultCalendarFactory;

import java.util.Scanner;


public class Main {

    //посилання на конструктор класу CalendarPerMonth -  CalendarPerMonth(int year, int month)
    private static final CalendarMonthFactory<CalendarPerMonth> monthFactory = CalendarPerMonth::new;
    //посилання на конструктор класу CalendarPerMonth -  CalendarPerMonth()
    private static final DefaultCalendarFactory<CalendarPerMonth> defaultFactory = CalendarPerMonth::new;

    public static void main(String[] args) {

        CalendarPerMonth calendarPerMonth;
        Scanner scan = new Scanner(System.in);
        int year, month;
        String mm, yyyy;

        System.out.println("Press \"Enter\" to display current month calendar" +
                "\nType the number of the month and year to display that month's calendar" +
                "\n(Month's range: 1 - 12; Year's range meets certain class Year values: MIN_VALUE and MAX_VALUE)");
        System.out.println("Input month:");
        mm = scan.nextLine();

        calendarPerMonth = defaultFactory.newCalendarSheet();

        if(!mm.isEmpty()) {
            System.out.println("Input year:");
            yyyy = scan.nextLine();

            if (CalendarPerMonth.isValidMonthValue(mm)
                    && CalendarPerMonth.isValidYear(yyyy)) {

                month = Integer.parseInt(mm);
                year = Integer.parseInt(yyyy);
                calendarPerMonth = monthFactory.newCalendarSheet(year, month);

            } else {
                System.out.println("Incorrect value entered");
                System.exit(0);
            }
        }
        //виведення календаря на екран
        calendarPerMonth.printCalendarSheet();
    }
}

