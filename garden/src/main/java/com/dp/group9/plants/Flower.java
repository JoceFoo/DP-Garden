package com.dp.group9.plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Flower extends PlantDecorator{
    Pane pane;
    ImageView flowerView;

    public Flower(Plant plant, Pane pane) {
        super(plant);
        this.pane = pane;

        Image flower = new Image(getClass().getResourceAsStream("/flower.png"));
        flowerView = new ImageView(flower);
        flowerView.setFitHeight(50);
        flowerView.setFitWidth(300);

        flowerView.setLayoutY(pane.getHeight() - flowerView.getFitHeight());
    }

    @Override
    public ImageView getView() {
        return flowerView;
    }

    @Override
    public void display() {
        plant.display();
        pane.getChildren().add(flowerView);
    }  
}
