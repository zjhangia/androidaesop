package com.dezhen.aesop.nav_fragment;

public class Collection_Card {
    public String co_title;
    public int co_imageResources;

    public Collection_Card(String co_title, int co_imageResources) {
        this.co_title = co_title;
        this.co_imageResources = co_imageResources;
    }

    public String getCoTitle() {return co_title;}
    public int getCoImageResource() {return co_imageResources;}
}
