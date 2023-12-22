package com.dp.group9;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Snail implements Animal {

    private boolean isSnailAnimationInProgress = false;
    private ImageView snailImageView;

    @Override
    public ImageView getImageView() {
        if (snailImageView == null) {
            snailImageView = new ImageView();
        }
        return snailImageView;
    }

    public void showAnimalImage() {
        try {
            Image snail = new Image(getClass().getResourceAsStream("/snail.png"));
            getImageView().setFitWidth(50);
            getImageView().setFitHeight(50);
            getImageView().setImage(snail);
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
        // Check if the snail animation is already in progress
        if (isSnailAnimationInProgress) {
            return;
        }

        // Snail animation is starting, set the flag to true
        isSnailAnimationInProgress = true;

        // Create a timeline for complex animations
        Timeline timeline = new Timeline();

        // Define the duration for each small movement
        double smallMovementDuration = 4.5; // Adjust the duration as needed for a slower movement

        // Add translation animation to the right
        KeyValue translateXValueRight = new KeyValue(imageView.translateXProperty(),
                500);
        KeyFrame translateKeyFrameRight = new KeyFrame(Duration.seconds(smallMovementDuration), translateXValueRight);

        // Add an event handler to flip the image when it reaches the right edge
        EventHandler<ActionEvent> flipHandler = event -> {
            imageView.setScaleX(-1); // Flip the image
        };
        KeyFrame flipKeyFrame = new KeyFrame(Duration.seconds(smallMovementDuration),
                flipHandler);

        // Add translation animation back to the left
        KeyValue translateXValueLeft = new KeyValue(imageView.translateXProperty(),
                350);
        KeyFrame translateKeyFrameLeft = new KeyFrame(Duration.seconds(smallMovementDuration * 2), translateXValueLeft);

        // Add an event handler to flip the image when it reaches the left edge
        EventHandler<ActionEvent> flipBackHandler = event -> {
            imageView.setScaleX(1); // Flip the image back to its original position
            // Snail animation is complete, set the flag back to false
            isSnailAnimationInProgress = false;
        };
        KeyFrame flipBackKeyFrame = new KeyFrame(Duration.seconds(smallMovementDuration * 2), flipBackHandler);

        // Add all keyframes to the timeline
        timeline.getKeyFrames().addAll(
                translateKeyFrameRight,
                flipKeyFrame,
                translateKeyFrameLeft,
                flipBackKeyFrame);

        // Set the cycle count (how many times the animation should repeat)
        timeline.setCycleCount(1);

        // Play the animation
        timeline.play();
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
