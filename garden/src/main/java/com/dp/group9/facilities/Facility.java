package com.dp.group9.facilities;

import javafx.scene.image.ImageView;

public abstract class Facility{
    private ImageView image;

    public abstract void display();
    public ImageView getView(){
        return image;
    }
}
