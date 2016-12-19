package com.company.classes;

import com.company.interfaces.CalendarControl;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

//клас CalendarPerMonth призначений для виведення календаря на місяць
public class CalendarPerMonth implements CalendarControl{

    private final List<String> shortDayNames = Arrays.asList(
            "Mo\t",
            "Tu\t",
            "We\t",
            "Th\t",
            "Fr\t",
            paintString("Sa", 31) + '\t',
            paintString("Su", 31) + '\n');
    //сьогоднішня дата
    private final LocalDate today = LocalDate.now();

    private LocalDate date;

    //створює об'єкт LocalDate за параметрами year та month
    public CalendarPerMonth(int year, int month){

        date = LocalDate.of(year, month, 1);
    }
    //створює об'єкт LocalDate за значннями сьогоднішнього року та місяця
    public CalendarPerMonth(){

        date = LocalDate.of(today.getYear(), today.getMonth(), 1);
    }

    //формує і виводить на екран у належному вигляді
    @Override
    public void printCalendarSheet() {

        int dayNum = 0, daysInMonth = date.lengthOfMonth();
        LocalDate monthDate;
        StringBuilder listCalendar = new StringBuilder();

        listCalendar.append("\nCalendar on\n")
                .append(date.getMonth())
                .append(' ')
                .append(date.getYear())
                .append('\n');

        //записуються короткі назви днів тижня
        shortDayNames.forEach(listCalendar::append);

        //табуляція до першого дня в місяці
        for(int i = 1; i < date.getDayOfWeek().getValue(); i++) { listCalendar.append('\t');}

        //формування календаря за числами місяця
        while (dayNum < daysInMonth){
            monthDate = date.plusDays(dayNum);

            listCalendar.append(getMonthDay(monthDate)).append('\t');

            if(monthDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                listCalendar.append('\n');

            dayNum++;
        }

        out.println(listCalendar);
    }

    //перевіряє число місяця і повертає його напис із відповідним кольором
    private String getMonthDay(LocalDate date) {

        if(date.isEqual(today)) {
            return paintString(date.getDayOfMonth() + "", 34);
        }

        if(date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return paintString(date.getDayOfMonth() + "", 31);
        }

        return date.getDayOfMonth() + "";

    }

    //зафарбовує текст у для виведення у консоль
    private String paintString(String string, int colorValue){

        return (char) 27 + "[" + colorValue + "m" + string + (char)27 + "[0m";

    }
    //перевіряє чи рядок є коректними номером місяця
    public static boolean isValidMonthValue(String s) {

        try {
            int month = Integer.parseInt(s);
            if(month <= 0 || month > 12) {
                return false;
            }
        }catch (NumberFormatException ex){
            return false;
        }
        return true;
    }
    //перевіряє чи рядок є коректними значенням року
    public static boolean isValidYear(String s){

        try {
            int year = Integer.parseInt(s);
            if(year < Year.MIN_VALUE && year > Year.MAX_VALUE) {
                return false;
            }
        }catch (NumberFormatException ex){
            return false;
        }

        return true;
    }
}
