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

public class Butterfly implements Animal {

    private boolean isButterflyAnimationInProgress = false;
    private ImageView butterflyImageView;

    @Override
    public ImageView getImageView() {
        if (butterflyImageView == null) {
            butterflyImageView = new ImageView();
        }
        return butterflyImageView;
    }

    public void showAnimalImage() {
        try {
            Image butterfly = new Image(getClass().getResourceAsStream("/butterfly.png"));
            getImageView().setFitWidth(50);
            getImageView().setFitHeight(50);
            getImageView().setImage(butterfly);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        showAnimalImage();
    }

    // without double click action
    // @Override
    // public void animate(ImageView imageView) {
    // // Get the current Y position of the butterfly
    // double currentY = imageView.getTranslateY();

    // // Create a timeline for the animation
    // Timeline timeline = new Timeline();

    // // Add keyframes to the timeline for smooth movement
    // KeyFrame keyFrameUp = new KeyFrame(
    // Duration.seconds(1),
    // new KeyValue(imageView.translateYProperty(), currentY - 50));

    // KeyFrame keyFrameDown = new KeyFrame(
    // Duration.seconds(2),
    // new KeyValue(imageView.translateYProperty(), currentY + 50));

    // KeyFrame keyFrameBack = new KeyFrame(
    // Duration.seconds(3),
    // new KeyValue(imageView.translateYProperty(), currentY));

    // // Add the keyframes to the timeline
    // timeline.getKeyFrames().addAll(keyFrameUp, keyFrameDown, keyFrameBack);

    // // Set the cycle count (how many times the animation should repeat)
    // // timeline.setCycleCount(Timeline.INDEFINITE);
    // timeline.setCycleCount(1);

    // // Play the animation
    // timeline.play();
    // }

    // // up down animate
    // @Override
    // public void animate(ImageView imageView) {
    // // Check if the animation is already in progress
    // if (isButterflyAnimationInProgress) {
    // return;
    // }

    // isButterflyAnimationInProgress = true;

    // // Get the current Y position of the butterfly
    // double currentY = imageView.getTranslateY();

    // // Create a timeline for the animation
    // Timeline timeline = new Timeline();

    // // Add keyframes to the timeline for smooth movement
    // KeyFrame keyFrameUp = new KeyFrame(
    // Duration.seconds(1),
    // new KeyValue(imageView.translateYProperty(), currentY - 50));

    // KeyFrame keyFrameDown = new KeyFrame(
    // Duration.seconds(2),
    // new KeyValue(imageView.translateYProperty(), currentY + 50));

    // KeyFrame keyFrameBack = new KeyFrame(
    // Duration.seconds(3),
    // new KeyValue(imageView.translateYProperty(), currentY));

    // // Add an event handler to reset the flag when the animation is complete
    // EventHandler<ActionEvent> onFinishedHandler = event -> {
    // isButterflyAnimationInProgress = false;
    // };

    // // Add the keyframes to the timeline
    // timeline.getKeyFrames().addAll(keyFrameUp, keyFrameDown, keyFrameBack);
    // timeline.setOnFinished(onFinishedHandler);

    // // Set the cycle count (how many times the animation should repeat)
    // timeline.setCycleCount(1);

    // // Play the animation
    // timeline.play();
    // }

    @Override
    public void animate(ImageView imageView) {
        // Check if the bird animation is already in progress
        if (isButterflyAnimationInProgress) {
            return;
        }

        isButterflyAnimationInProgress = true;

        // Create a timeline for complex animations
        Timeline timeline = new Timeline();

        // Get the width of the scene
        double sceneWidth = 700;

        // Define a fixed ratio for the animation duration
        double animationDurationRatio = 0.0020; // Adjust the ratio as needed

        // Calculate the animation duration based on the scene width and the fixed ratio
        double animationDuration = sceneWidth * animationDurationRatio;

        // Add translation animation to the right
        KeyValue translateXValueRight = new KeyValue(imageView.translateXProperty(), 500);
        KeyFrame translateKeyFrameRight = new KeyFrame(Duration.seconds(animationDuration), translateXValueRight);

        // Add an event handler to flip the image when it reaches the right edge
        EventHandler<ActionEvent> flipHandler = event -> {
            imageView.setScaleX(-1); // Flip the image
        };
        KeyFrame flipKeyFrame = new KeyFrame(Duration.seconds(animationDuration), flipHandler);

        // Add translation animation back to the left
        KeyValue translateXValueLeft = new KeyValue(imageView.translateXProperty(), 750);
        KeyFrame translateKeyFrameLeft = new KeyFrame(Duration.seconds(animationDuration * 2), translateXValueLeft);

        // Add an event handler to flip the image when it reaches the left edge
        EventHandler<ActionEvent> flipBackHandler = event -> {
            imageView.setScaleX(1); // Flip the image back to its original position
            // Bird animation is complete, set the flag back to false
            isButterflyAnimationInProgress = false;
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
