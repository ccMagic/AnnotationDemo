package com.github.ccmagic.usercase;

import com.github.ccmagic.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracer {
    public static void traceUserCases(List<Integer> userCases, Class<?> cl) {
        for (Method method : cl.getDeclaredMethods()) {
            UserCase userCase = method.getAnnotation(UserCase.class);
            if (userCase != null) {
                Log.i("Found Use Case : " + userCase.id() + " " + userCase.description());
                userCases.remove(new Integer(userCase.id()));
            }
        }
        for (int i : userCases) {
            Log.w("Missing use case - " + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> userCases = new ArrayList<>();
        Collections.addAll(userCases, 1, 2, 3, 4, 5, 6, 7);
        traceUserCases(userCases, PasswordUtils.class);
    }
}
