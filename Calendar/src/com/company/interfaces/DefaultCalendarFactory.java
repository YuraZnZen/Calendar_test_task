package com.company.interfaces;

import com.company.classes.CalendarPerMonth;

@FunctionalInterface
public interface DefaultCalendarFactory<CS extends CalendarPerMonth>{

    CS newCalendarSheet();

}