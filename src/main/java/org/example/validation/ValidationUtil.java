package org.example.validation;

import org.example.exceptions.BadArgumentException;

/**
 * Utility class to check input arguments.
 */
public class ValidationUtil {

    public static void checkNotEmpty(String s) {
        checkNotNull(s);
        if ("".equals(s)) {
            throw new BadArgumentException("Some request argument is empty");
        }
    }

    public static void checkNotNull(Object o) {
        if (o == null) {
            throw new BadArgumentException("Some request argument is null");
        }
    }

    public static void checkPositive(int number) {
        if (number < 1) {
            throw new BadArgumentException("Quantity cannot be less then 1");
        }
    }

}
