package com.dp.group9;

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

    // @Override
    // public void animate(ImageView imageView) {
    // // Create a TranslateTransition for the frog jump animation
    // TranslateTransition jumpUp = new TranslateTransition(Duration.seconds(0.5),
    // imageView);
    // jumpUp.setByY(-50); // Adjust the jump height as needed

    // // Create a second TranslateTransition for coming back down
    // TranslateTransition jumpDown = new TranslateTransition(Duration.seconds(0.5),
    // imageView);
    // jumpDown.setByY(50); // Adjust the jump height as needed

    // // Set event handlers for each transition to make it continuous
    // jumpUp.setOnFinished(event -> jumpDown.play());
    // jumpDown.setOnFinished(event -> jumpUp.play());

    // // Start the first jump animation
    // jumpUp.play();
    // }

    @Override
    public void animate(ImageView imageView) {
        // Check if the frog animation is already in progress
        if (isFrogAnimationInProgress) {
            return;
        }

        // Frog animation is starting, set the flag to true
        isFrogAnimationInProgress = true;

        // Create a TranslateTransition for the frog jump animation
        TranslateTransition jumpUp = new TranslateTransition(Duration.seconds(0.5), imageView);
        jumpUp.setByY(-50); // Adjust the jump height as needed

        // Create a second TranslateTransition for coming back down
        TranslateTransition jumpDown = new TranslateTransition(Duration.seconds(0.5), imageView);
        jumpDown.setByY(50); // Adjust the jump height as needed

        // Set event handlers for each transition to make it continuous
        jumpUp.setOnFinished(event -> jumpDown.play());
        jumpDown.setOnFinished(event -> {
            jumpCount++;

            // Check if the desired number of cycles is reached
            if (jumpCount < 3) {
                // Continue the animation
                jumpUp.play();
            } else {
                // Reset the jump count and stop the animation
                jumpCount = 0;
                isFrogAnimationInProgress = false;
            }
        });

        // Start the first jump animation
        jumpUp.play();
    }

    @Override
    public void setupMouseHandlers() {
        getImageView().setOnMouseEntered(this::handleMouseEntered);
        getImageView().setOnMouseExited(this::handleMouseExited);
        getImageView().setOnMouseClicked(this::handleMouseClicked);
    }

    private void handleMouseEntered(MouseEvent event) {
        // Set the cursor to indicate that the bird is clickable
        ImageView sourceImageView = (ImageView) event.getSource();
        sourceImageView.setCursor(Cursor.HAND);
    }

    private void handleMouseExited(MouseEvent event) {
        // Set the default cursor when not hovering over the bird
        ImageView sourceImageView = (ImageView) event.getSource();
        sourceImageView.setCursor(Cursor.DEFAULT);
    }

    private void handleMouseClicked(MouseEvent event) {
        // Perform a complex animation when the bird is clicked
        animate(getImageView());
    }
}
