package com.dezhen.aesop.nav_fragment;

public class BBC_Card {
    public int cardView;
    public String title;
    public int imageResources;

    public BBC_Card(String title, int imageResources) {
        this.title = title;
        this.imageResources = imageResources;
    }

    public int getCardView() {return cardView;}
    public String getTitle() {return title;}
    public int getImageResource() {return imageResources;}
}
