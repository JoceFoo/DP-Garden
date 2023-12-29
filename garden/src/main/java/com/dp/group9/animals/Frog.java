package com.dp.group9.animals;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Frog implements Animal {

    private boolean isFrogAnimationInProgress = false;
    private ImageView frogImageView;
    private int jumpCount = 0;

    @Override
    public ImageView getImageView() {
        if (frogImageView == null) {
            frogImageView = new ImageView();
        }
        return frogImageView;
    }

    public void showAnimalImage() {
        try {
            Image frog = new Image(getClass().getResourceAsStream("/frog.png"));
            getImageView().setFitWidth(50);
            getImageView().setFitHeight(50);
            getImageView().setImage(frog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        showAnimalImage();
    }

    @Override
    public void animate(ImageView imageView) {
        if (isFrogAnimationInProgress) {
            return;
        }

        isFrogAnimationInProgress = true;

        TranslateTransition jumpUp = new TranslateTransition(Duration.seconds(0.5), imageView);
        jumpUp.setByY(-50);

        TranslateTransition jumpDown = new TranslateTransition(Duration.seconds(0.5), imageView);
        jumpDown.setByY(50); // Adjust the jump height as needed

        jumpUp.setOnFinished(event -> jumpDown.play());
        jumpDown.setOnFinished(event -> {
            jumpCount++;

            // Check the number of cycles
            if (jumpCount < 3) {
                jumpUp.play();
            } else {
                jumpCount = 0;
                isFrogAnimationInProgress = false;
            }
        });

        jumpUp.play();
    }

    @Override
    public void setupMouseHandlers() {
        getImageView().setOnMouseEntered(this::handleMouseEntered);
        getImageView().setOnMouseExited(this::handleMouseExited);
        getImageView().setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseEntered(MouseEvent event) {
        ImageView sourceImageView = (ImageView) event.getSource();
        sourceImageView.setCursor(Cursor.HAND);
    }

    private void handleMouseExited(MouseEvent event) {
        ImageView sourceImageView = (ImageView) event.getSource();
        sourceImageView.setCursor(Cursor.DEFAULT);
    }

    private void handleMouseClicked(MouseEvent event) {
        animate(getImageView());
    }
}
