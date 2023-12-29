package com.dp.group9.animals;

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

        double sceneWidth = 700;

        double animationDurationRatio = 0.0030;

        // Calculate the animation duration based on the scene width and the fixed ratio
        double animationDuration = sceneWidth * animationDurationRatio;

        // Move back to the right
        KeyValue translateXValueRight = new KeyValue(imageView.translateXProperty(), 950);
        KeyFrame translateKeyFrameRight = new KeyFrame(Duration.seconds(animationDuration), translateXValueRight);

        // Flip the image when it reaches the right edge
        EventHandler<ActionEvent> flipHandler = event -> {
            imageView.setScaleX(-1);
        };
        KeyFrame flipKeyFrame = new KeyFrame(Duration.seconds(animationDuration), flipHandler);

        // Move back to the left
        KeyValue translateXValueLeft = new KeyValue(imageView.translateXProperty(), 15);
        KeyFrame translateKeyFrameLeft = new KeyFrame(Duration.seconds(animationDuration * 2), translateXValueLeft);

        // Flip the image again
        EventHandler<ActionEvent> flipBackHandler = event -> {
            imageView.setScaleX(1);
            isBirdAnimationInProgress = false;
        };
        KeyFrame flipBackKeyFrame = new KeyFrame(Duration.seconds(animationDuration * 2), flipBackHandler);

        timeline.getKeyFrames().addAll(
                translateKeyFrameRight,
                flipKeyFrame,
                translateKeyFrameLeft,
                flipBackKeyFrame);

        timeline.setCycleCount(1);

        timeline.play();

    }

    @Override
    public void setupMouseHandlers() {
        getImageView().setOnMouseEntered(this::handleMouseEntered);
        getImageView().setOnMouseExited(this::handleMouseExited);
        getImageView().setOnMouseClicked(this::handleMouseClicked);
    }

    // Set the cursor to indicate that the bird is clickable
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
