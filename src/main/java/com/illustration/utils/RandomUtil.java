package com.illustration.utils;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.Random;

public class RandomUtil {
    private static final String BASE_NUMBER = "0123456789";

    private static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    private static final String BASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER + BASE_ALPHABET;
    private static final String FORMAT = "0.00";
    private static final Random random = new Random();

    public static int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static int randomInt(int limit) {
        return random.nextInt(limit);
    }

    public static int randomInt() {
        return random.nextInt();
    }

    public static long randomLong() {
        return random.nextLong();
    }

    public static long randomLong(long min, long max) {
        return min + (((long) (new Random().nextDouble() * (max - min))));
    }

    public static String randomString(int length) {
        return randomString(BASE_CHAR_NUMBER, length);
    }

    public static String randomNumbers(int length) {
        return randomString(BASE_NUMBER, length);
    }

    public static String randomString(String baseString, int length) {
        StringBuilder sb = new StringBuilder();

        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(baseLength);
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }

    public static double randomDouble(final double min, final double max) {
        return min + ((max - min) * random.nextDouble());
    }

    public static double randomDouble() {
        return randomDouble(0.00, 100.00);
    }

    public static String randomDouble(String format) {
        return new DecimalFormat(format).format(randomDouble());
    }

    public static String randomValueByType(String type) {
        switch (type) {
            //12
            case "char":
                return randomString(1);
            case "Integer":    //4
            case "int":
            case "Long": //-5
            case "long":
            case "BigDecimal":    //3
            case "BigInteger":
                return String.valueOf(randomInt(1000));
            case "Double": //8
            case "double":
            case "Float": //6
            case "float":
                return String.valueOf(randomDouble(FORMAT));
            case "short":
            case "Short":
                return String.valueOf(randomInt(0, 32767));
            case "Byte":
            case "byte":
                return String.valueOf(randomInt(0, 127));
            case "boolean":
            case "Boolean":
                return "true";
            case "OffsetDateTime":
                return OffsetDateTime.now().toString();
            default:
                return randomString(6);
        }
    }

    public static String generateDefaultValueByType(String type) {
        switch (type) {
            //12
            case "char":
                return "";
            case "Integer":    //4
            case "int":
            case "Long": //-5
            case "long":
            case "BigDecimal":    //3
            case "BigInteger":
                return "0";
            case "Double": //8
            case "double":
            case "Float": //6
            case "float":
                return "0.0";
            case "short":
            case "Short":
                return "0";
            case "boolean":
            case "Boolean":
                return "true";
            default:
                return "";
        }
    }
}
