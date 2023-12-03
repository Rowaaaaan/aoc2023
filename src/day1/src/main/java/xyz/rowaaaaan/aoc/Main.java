package xyz.rowaaaaan.aoc;

import java.io.File;
import java.util.Scanner;
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
                int tens = 0;
                int ones = 0;
                LOGGER.info(() -> "Current line: " + line);

                // Get tens digit.
                // If the character is an integer, break.
                // Otherwise, check if the character is a starting
                // letter for any of the numbers.
                // If so, then parse the substring if it is a number.
                for (int i = 0; i < line.length(); i++) {
                    if (isDigit(line.charAt(i))) {
                        LOGGER.log(Level.INFO, "Found tens digit: {0}", line.charAt(i));
                        tens = Character.getNumericValue(line.charAt(i));
                        break;
                    } else {
                        tens = parseStringNum(line.substring(i));
                        if (tens != 0) {
                            break;
                        }
                    }
                }

                // Get ones digit
                for (int j = line.length() - 1; j >= 0; j--) {
                    if (isDigit(line.charAt(j))) {
                        ones = Character.getNumericValue(line.charAt(j));
                        LOGGER.log(Level.INFO, "Found ones digit: {0}", ones);
                        break;
                    } else {
                        ones = parseStringNum(line.substring(j));
                        if (ones != 0) {
                            LOGGER.log(Level.INFO, "Found ones digit: {0}", ones);
                            break;
                        }
                    }
                }
                calibrationSum += (tens * 10) + ones;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception caught: {0}", e.getMessage());
        }

        LOGGER.log(Level.INFO, "Calibration sum: {0}", calibrationSum);
    }

    private static int parseStringNum(String str) {
        LOGGER.log(Level.INFO, "Parsing substring {0}", str);
        int num = 0;
        switch (str.charAt(0)) {
            case 'o': {
                if (str.length() < 3) {
                    num = 0;
                } else if (str.substring(0, 3).equals("one")) {
                    num = 1;
                }
                break;
            }
            case 't': {
                if ((str.length() >= 3) && str.substring(0, 3).equals("two")) {
                    num = 2;
                } else if ((str.length() >= 5) && str.substring(0, 5).equals("three")) {
                    num = 3;
                }
                break;
            }
            case 'f': {
                if (str.length() < 4) {
                    num = 0;
                } else if (str.substring(0, 4).equals("four")) {
                    num = 4;
                } else if (str.substring(0, 4).equals("five")) {
                    num = 5;
                }
                break;
            }
            case 's': {
                if ((str.length() >= 3) && str.substring(0, 3).equals("six")) {
                    num = 6;
                } else if ((str.length() >= 5) && str.substring(0, 5).equals("seven")) {
                    num = 7;
                }
                break;
            }
            case 'e': {
                if (str.length() < 5) {
                    num = 0;
                } else if (str.substring(0, 5).equals("eight")) {
                    num = 8;
                }
                break;
            }
            case 'n': {
                if (str.length() < 4) {
                    num = 0;
                } else if (str.substring(0, 4).equals("nine")) {
                    num = 9;
                }
                break;
            }
            default:
                num = 0;
                break;
        }
        if (num != 0) {
            LOGGER.log(Level.INFO, "Detected number: {0}", num);
        }
        return num;
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