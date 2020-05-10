package com.github.ccmagic;

public class Log {
    public static void d(String msg) {
        System.out.println("Debug: " + msg);
    }

    public static void i(String msg) {
        System.out.println("Info: " + msg);
    }

    public static void w(String msg) {
        System.out.println("Warning: " + msg);
    }

    public static void e(String msg) {
        System.out.println("Error: " + msg);
    }

    public static void e(Exception exception) {
        exception.printStackTrace();
    }
}
