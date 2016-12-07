package com.lohika.util;

public class MyLog {
    public static void log(String message) {
        System.out.println(System.currentTimeMillis() + " " + message);
    }

    public static void log(long message) {
        System.out.println(System.currentTimeMillis() + " " + message);
    }
}
