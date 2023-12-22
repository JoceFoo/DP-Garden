package com.dp.group9;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Bird implements Animal {

    private boolean isBirdAnimationInProgress = false;
    private ImageView birdImageView;

    @Override
    public ImageView getImageView() {
        if (birdImageView == null) {
            birdImageView = new ImageView();
        }
        return birdImageView;
    }

    public void showAnimalImage() {
        try {
            Image bird = new Image(getClass().getResourceAsStream("/bird.png"));
            getImageView().setFitWidth(50);
            getImageView().setFitHeight(50);
            getImageView().setImage(bird);
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
        // Check if the bird animation is already in progress
        if (isBirdAnimationInProgress) {
            return;
        }

        isBirdAnimationInProgress = true;

        // Create a timeline for complex animations
        Timeline timeline = new Timeline();

        // Get the width of the scene
        double sceneWidth = 700;

        // Define a fixed ratio for the animation duration
        double animationDurationRatio = 0.0030; // Adjust the ratio as needed

        // Calculate the animation duration based on the scene width and the fixed ratio
        double animationDuration = sceneWidth * animationDurationRatio;

        // Add translation animation to the right
        KeyValue translateXValueRight = new KeyValue(imageView.translateXProperty(), 950);
        KeyFrame translateKeyFrameRight = new KeyFrame(Duration.seconds(animationDuration), translateXValueRight);

        // Add an event handler to flip the image when it reaches the right edge
        EventHandler<ActionEvent> flipHandler = event -> {
            imageView.setScaleX(-1); // Flip the image
        };
        KeyFrame flipKeyFrame = new KeyFrame(Duration.seconds(animationDuration), flipHandler);

        // Add translation animation back to the left
        KeyValue translateXValueLeft = new KeyValue(imageView.translateXProperty(), 15);
        KeyFrame translateKeyFrameLeft = new KeyFrame(Duration.seconds(animationDuration * 2), translateXValueLeft);

        // Add an event handler to flip the image when it reaches the left edge
        EventHandler<ActionEvent> flipBackHandler = event -> {
            imageView.setScaleX(1); // Flip the image back to its original position
            // Bird animation is complete, set the flag back to false
            isBirdAnimationInProgress = false;
        };
        KeyFrame flipBackKeyFrame = new KeyFrame(Duration.seconds(animationDuration * 2), flipBackHandler);

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

        // Play the sound after the animation is triggered
        // playBirdSound();
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
