package xyz.rowaaaaan.aoc;

import java.io.File;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// Parse a text file line by line
// Read the line until I find a number,
// then break out of loop and loop again
// starting from the end of the string
//
// If I return to the index of the first number,
// add the number to itself.
// 
// Part 2:
// Numbers up to two digits can be spelled out in words.
// These must also be taken into account in addition with
// the numbers I'm already parsing.
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.ALL);

        int calibrationSum = 0;
        String filename = args[0];
        File file = new File(filename);
        LOGGER.log(Level.INFO, "Filename: {0}", filename);

        try (Scanner input = new Scanner(file)) {

            while (input.hasNext()) {
                String line = input.nextLine();
                LOGGER.info(() -> "Current line: " + line);

                // Get tens digit
                for (int i = 0; i < line.length(); i++) {
                    if (isDigit(line.charAt(i))) {
                        LOGGER.log(Level.INFO, "Found tens digit: {0}", line.charAt(i));
                        calibrationSum += Character.getNumericValue(line.charAt(i)) * 10;
                        break;
                    }
                }

                // Get ones digit
                for (int j = line.length() - 1; j >= 0; j--) {
                    if (isDigit(line.charAt(j))) {
                        LOGGER.log(Level.INFO, "Found ones digit: {0}", line.charAt(j));
                        calibrationSum += Character.getNumericValue(line.charAt(j));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }

        LOGGER.log(Level.INFO, "Calibration sum: {0}", calibrationSum);
    }

    private static boolean isDigit(char c) {
        switch (c) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '0':
                return true;
            default:
                return false;
        }
    }
}