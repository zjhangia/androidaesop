package com.dezhen.aesop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadStreamFormText {

    public String getString(InputStream inputStream){
        InputStreamReader inputStreamReader = null;
        try{
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuffer = new StringBuilder("");
        String line;
        try{
            while((line = reader.readLine()) != null){
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
