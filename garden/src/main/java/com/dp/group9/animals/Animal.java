package com.dp.group9.animals;

import javafx.scene.image.ImageView;

public interface Animal {

    ImageView getImageView();

    void display();

    void setupMouseHandlers();

    void animate(ImageView imageView);

}
