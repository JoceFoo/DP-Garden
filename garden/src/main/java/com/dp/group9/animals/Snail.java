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
        if (isSnailAnimationInProgress) {
            return;
        }

        isSnailAnimationInProgress = true;

        Timeline timeline = new Timeline();

        double smallMovementDuration = 4.5;

        KeyValue translateXValueRight = new KeyValue(imageView.translateXProperty(),
                500);
        KeyFrame translateKeyFrameRight = new KeyFrame(Duration.seconds(smallMovementDuration), translateXValueRight);

        EventHandler<ActionEvent> flipHandler = event -> {
            imageView.setScaleX(-1); // Flip the image
        };
        KeyFrame flipKeyFrame = new KeyFrame(Duration.seconds(smallMovementDuration),
                flipHandler);

        KeyValue translateXValueLeft = new KeyValue(imageView.translateXProperty(),
                350);
        KeyFrame translateKeyFrameLeft = new KeyFrame(Duration.seconds(smallMovementDuration * 2), translateXValueLeft);

        EventHandler<ActionEvent> flipBackHandler = event -> {
            imageView.setScaleX(1);
            isSnailAnimationInProgress = false;
        };
        KeyFrame flipBackKeyFrame = new KeyFrame(Duration.seconds(smallMovementDuration * 2), flipBackHandler);

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
