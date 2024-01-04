package com.dp.group9.plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Grass extends PlantDecorator {
    Pane pane;
    ImageView grassView;

    public Grass(Plant plant, Pane pane) {
        super(plant);
        this.pane = pane;

        Image grass = new Image(getClass().getResourceAsStream("/grass.png"));
        grassView = new ImageView(grass);
        grassView.setFitHeight(50);
        grassView.setFitWidth(300);

        grassView.setLayoutY(pane.getHeight() - grassView.getFitHeight());
    }

    @Override
    public ImageView getView() {
        return grassView;
    }

    @Override
    public void display() {
        pane.getChildren().add(grassView);
    }
}
