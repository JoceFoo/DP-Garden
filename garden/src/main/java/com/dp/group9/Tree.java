package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tree extends PlantDecorator {
    Pane pane;
    ImageView treeView;

    public Tree(Plant plant, Pane pane) {
        super(plant);
        this.pane = pane;

        Image tree = new Image(getClass().getResourceAsStream("/tree.png"));
        treeView = new ImageView(tree);
        treeView.setFitHeight(450);
        treeView.setFitWidth(450);

        treeView.setLayoutY(pane.getHeight() - treeView.getFitHeight());
    }

    @Override
    public ImageView getView() {
        return treeView;
    }

    @Override
    public void display() {
        pane.getChildren().add(treeView);
    }
}
