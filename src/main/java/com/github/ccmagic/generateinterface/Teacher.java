package com.github.ccmagic.generateinterface;

import com.github.ccmagic.Log;

@GenerateInterface(suffix = "IntSuffix")
public class Teacher {
    //教书
    private void teach() {
        Log.i("teach...");
    }

    //行走
    public void walk() {
        Log.i("walking");
    }
}
