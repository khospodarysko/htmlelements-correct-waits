package com.lohika.util;

public class Wait {
    public static void seconds(int seconds) {
        MyLog.log("Wait " + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public static void miliseconds(int miliseconds) {
        MyLog.log("Wait " + miliseconds + " miliseconds");
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
