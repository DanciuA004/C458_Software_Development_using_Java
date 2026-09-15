package com.mthree.intermediate_java.birthday_calculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class BirthDAYCalculator {
    public static void main(String[] args) {
        System.out.println("\nWelcome to the Magical BirthDAY Calculator!\n");

        // Getting user input
        System.out.print("What's your birthday? (DD/MM/YYYY): ");
        Scanner input = new Scanner(System.in);
        String birthday = input.nextLine();

        // Formater for the structure of the date
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // From String variable to LocalDate object
        LocalDate birthDate = LocalDate.parse(birthday, inputFormat);

        // EEEE means the full weekday name.
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("EEEE");

        System.out.println("That means you were born on a " +
                birthDate.format(dayFormat) + "!");

        // Overriding the birthday year to this year
        // still using dayFormat for the day of the week
        LocalDate birthdayThisYear = birthDate.withYear(LocalDate.now().getYear());

        System.out.println("This year, your birthday falls on a " +
                birthdayThisYear.format(dayFormat) + "!");

        System.out.println("And since today is " + LocalDate.now() + ", there's only " + birthdayThisYear.until(LocalDate.now()) + " more days until the next one!");
    }
}
