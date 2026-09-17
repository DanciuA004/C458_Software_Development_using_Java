package com.mthree.address_book.ui;

import java.util.Scanner;

/**
 * This class handles all user output and input from the console.
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner scanner = new Scanner(System.in);
    String inputString;
    int inputInteger;
    double inputDouble;
    float inputFloat;
    long inputLong;

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        inputString = scanner.nextLine();
        return inputString;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        inputInteger = Integer.parseInt(scanner.nextLine());
        return inputInteger;
    }

    public int readInt(String prompt, int min, int max) {
        inputInteger = readInt(prompt);
        if (inputInteger < min ||  inputInteger > max) {
            readInt(prompt, min, max);
        }
        return inputInteger;
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        inputDouble = Double.parseDouble(scanner.nextLine());
        return inputDouble;
    }

    public double readDouble(String prompt, double min, double max) {
        inputDouble = readDouble(prompt);
        if (inputDouble < min ||  inputDouble > max) {
            readDouble(prompt, min, max);
        }
        return inputDouble;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        inputFloat = Float.parseFloat(scanner.nextLine());
        return inputFloat;
    }

    public float readFloat(String prompt, float min, float max) {
        inputFloat = readFloat(prompt);
        if (inputFloat < min ||  inputFloat > max) {
            readFloat(prompt, min, max);
        }
        return inputFloat;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        inputLong = Long.parseLong(scanner.nextLine());
        return inputLong;
    }

    public long readLong(String prompt, long min, long max) {
        inputLong = readLong(prompt);
        if (inputLong < min ||  inputLong > max) {
            readLong(prompt, min, max);
        }
        return inputLong;
    }
}
