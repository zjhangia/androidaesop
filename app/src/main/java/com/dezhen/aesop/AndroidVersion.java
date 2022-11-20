package com.dezhen.aesop;

import android.os.Build;

public class AndroidVersion {
    private static int version;
    public static int getAndroidSystemVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            version = 10;
        }else version = 9;

        return version;
    }
}
