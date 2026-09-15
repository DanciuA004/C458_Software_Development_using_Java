package com.mthree.intermediate_java.enums;

import java.util.Scanner;

public class DaysUntilFriday {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        DaysUntilFriday obj = new DaysUntilFriday();
        DaysOfTheWeek day = obj.whatDayIsIt();
        System.out.println(obj.howManyDays(day) + " day(s) until Friday");
    }

    public DaysOfTheWeek whatDayIsIt() {
        System.out.println("what day is it?");
        String day = input.nextLine();

        switch (day) {
            case "Monday":
                return DaysOfTheWeek.MONDAY;
            case "Tuesday":
                return DaysOfTheWeek.TUESDAY;
            case "Wednesday":
                return DaysOfTheWeek.WEDNESDAY;
            case "Thursday":
                return DaysOfTheWeek.THURSDAY;
            case "Friday":
                return DaysOfTheWeek.FRIDAY;
            case "Saturday":
                return DaysOfTheWeek.SATURDAY;
            case "Sunday":
                return DaysOfTheWeek.SUNDAY;
            default:
                return null;
        }
    }

    public int howManyDays(DaysOfTheWeek day) {
        switch (day) {
            case MONDAY:
             return 4;
            case TUESDAY:
             return 3;
            case WEDNESDAY:
               return 2;
            case THURSDAY:
               return 1;
            case FRIDAY:
               return 0;
            case SATURDAY:
               return 6;
            case SUNDAY:
              return 5;
            default:
              return -1;
        }
    }
}
