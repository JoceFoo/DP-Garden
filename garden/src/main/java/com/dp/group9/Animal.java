package com.dp.group9;

import javafx.scene.image.ImageView;

// public interface Animal {
//     public String getImagePath();

//     public void animate(ImageView imageView);
// }

public interface Animal {

    ImageView getImageView();

    void display();

    void setupMouseHandlers();

    void animate(ImageView imageView);

}
