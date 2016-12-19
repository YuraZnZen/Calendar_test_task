package com.company.interfaces;

import com.company.classes.CalendarPerMonth;

@FunctionalInterface
public interface CalendarMonthFactory<CS extends CalendarPerMonth>{

    CS newCalendarSheet(int year, int month);

}