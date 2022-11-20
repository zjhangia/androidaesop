package com.dezhen.aesop;

import java.io.File;

public class CheckFileExist {
    public static boolean isFileExist(String path) {
        return new File(path).exists();
    }

}
