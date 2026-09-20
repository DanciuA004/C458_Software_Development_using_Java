package com.wiley_edge_corp.flooring_mastery.view;

import java.util.Scanner;

/**
 * The UserIO handles all output and input to the console
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner scanner = new Scanner(System.in);
    String inputString;
    int inputInteger;
    double inputDouble;
    float inputFloat;
    long inputLong;

    /**
     * Output a message
     * @param message string to output
     */
    @Override
    public void print(String message) {
        System.out.println(message);

    }

    /**
     * Input for String
     *
     * @param prompt String message to indicate what user should input
     * @return user input String
     */
    @Override
    public String readString(String prompt) {
        print(prompt);
        inputString = scanner.nextLine();
        return inputString;
    }

    /**
     * Input for Integer
     *
     * @param prompt String message to indicate what user should input
     * @return user input Integer if valid, else -1
     */
    @Override
    public int readInt(String prompt) {
        print(prompt);

        try {
            inputInteger = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }

        return inputInteger;
    }

    /**
     * Input for constrained Integer
     *
     * @param prompt String message to indicate what user should input
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return user input Integer if valid, else -1
     */
    public int readInt(String prompt, int min, int max) {
        inputInteger = readInt(prompt);

        if (inputInteger == -1) {
            return -1;
        }

        if (inputInteger < min ||  inputInteger > max) {
            readInt(prompt, min, max);
        }

        return inputInteger;
    }

    /**
     * Input for Double
     *
     * @param prompt String message to indicate what user should input
     * @return user input Double if valid, else -1
     */
    @Override
    public double readDouble(String prompt) {
        print(prompt);

        try {
            inputDouble = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }

        return inputDouble;
    }

    /**
     * Input for constrained Double
     *
     * @param prompt String message to indicate what user should input
     * @param min minimum value (inclusive)
     * @param max maximum value (inclusive)
     * @return user input Double if valid, else -1
     */
    public double readDouble(String prompt, double min, double max) {
        inputDouble = readDouble(prompt);

        if (inputDouble == -1) {
            return -1;
        }

        if (inputDouble < min ||  inputDouble > max) {
            readDouble(prompt, min, max);
        }

        return inputDouble;
    }

    /**
     * Input for Float
     *
     * @param prompt String message to indicate what user should input
     * @return user input Float if valid, else -1
     */
    @Override
    public float readFloat(String prompt) {
        print(prompt);

        try {
            inputFloat = Float.parseFloat(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }

        return inputFloat;
    }

    /**
     * Input for constrained Float
     *
     * @param prompt String message to indicate what user should input
     * @param min minimum (inclusive)
     * @param max maximum (inclusive)
     * @return user input Float if valid, else -1
     */
    public float readFloat(String prompt, float min, float max) {
        inputFloat = readFloat(prompt);

        if (inputFloat == -1) {
            return -1;
        }

        if (inputFloat < min ||  inputFloat > max) {
            readFloat(prompt, min, max);
        }

        return inputFloat;
    }

    /**
     * Input for Long
     *
     * @param prompt String message to indicate what user should input
     * @return user input Long if valid, else -1
     */
    @Override
    public long readLong(String prompt) {
        print(prompt);

        try {
            inputLong = Long.parseLong(scanner.nextLine());
        }  catch (Exception e) {
            return -1;
        }

        return inputLong;
    }

    /**
     * Input for constrained Long
     *
     * @param prompt String message to indicate what user should input
     * @param min minimum (inclusive)
     * @param max maximum (inclusive)
     * @return user input Long if valid, else -1
     */
    public long readLong(String prompt, long min, long max) {
        inputLong = readLong(prompt);

        if (inputLong == -1) {
            return -1;
        }
        if (inputLong < min ||  inputLong > max) {
            readLong(prompt, min, max);
        }

        return inputLong;
    }
}
