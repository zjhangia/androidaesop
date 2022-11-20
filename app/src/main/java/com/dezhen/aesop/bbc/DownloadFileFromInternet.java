package com.dezhen.aesop.bbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileFromInternet {
    private String url_string;
    private String folder_path;
    private String file_name;

    public DownloadFileFromInternet(String url_string, String folder_path, String file_name) {
        this.url_string = url_string;
        this.folder_path = folder_path;
        this.file_name = file_name;
    }

    public void getFileFromInternet(){
        int byteread = 0;
        try{
            URL url = new URL(url_string);
            URLConnection conn = (URLConnection)url.openConnection();

            InputStream inputStream = conn.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(folder_path + file_name);
            byte[] buff = new byte[1 << 10];
            while((byteread = inputStream.read(buff)) != -1){
                fileOutputStream.write(buff, 0, byteread);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
