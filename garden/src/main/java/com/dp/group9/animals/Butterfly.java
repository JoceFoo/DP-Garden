package com.dp.group9.animals;

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

    @Override
    public void display() {
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
    public void animate(ImageView imageView) {
        if (isButterflyAnimationInProgress) {
            return;
        }

        isButterflyAnimationInProgress = true;

        // Create a timeline for complex animations
        Timeline timeline = new Timeline();

        double sceneWidth = 700;

        double animationDurationRatio = 0.0020;

        // Calculate the animation duration based on the scene width and the fixed ratio
        double animationDuration = sceneWidth * animationDurationRatio;

        KeyValue translateXValueRight = new KeyValue(imageView.translateXProperty(), 500);
        KeyFrame translateKeyFrameRight = new KeyFrame(Duration.seconds(animationDuration), translateXValueRight);

        EventHandler<ActionEvent> flipHandler = event -> {
            imageView.setScaleX(-1); // Flip the image
        };
        KeyFrame flipKeyFrame = new KeyFrame(Duration.seconds(animationDuration), flipHandler);

        KeyValue translateXValueLeft = new KeyValue(imageView.translateXProperty(), 750);
        KeyFrame translateKeyFrameLeft = new KeyFrame(Duration.seconds(animationDuration * 2), translateXValueLeft);

        EventHandler<ActionEvent> flipBackHandler = event -> {
            imageView.setScaleX(1);
            isButterflyAnimationInProgress = false;
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
