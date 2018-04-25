package com.example.olga.vog;


public interface FeedItem {
    //This is a template for the FeedItem classes (like DTO, entities etc).

    int getID ();

    String getBodyType();

    String getMediaLocation();

    String getText();

}
