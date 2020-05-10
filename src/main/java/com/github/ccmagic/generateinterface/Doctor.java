package com.github.ccmagic.generateinterface;

import com.github.ccmagic.Log;

@GenerateInterface(suffix = "IntSuffix")
public class Doctor {
    //诊断
    private void diagnose() {
        Log.i("diagnose...");
    }

    //行走
    public void walk() {
        Log.i("walking");
    }
}
