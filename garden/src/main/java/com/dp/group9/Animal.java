package com.dp.group9;

import javafx.scene.image.ImageView;

public interface Animal {

    ImageView getImageView();

    void display();

    void setupMouseHandlers();

    void animate(ImageView imageView);

}
