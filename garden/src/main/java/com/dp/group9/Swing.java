package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Swing implements Facility{

    Pane pane;
    private ImageView swingView;

    public Swing(Pane pane, double x, double y){
        this.pane = pane;
        Image slide = new Image(getClass().getResourceAsStream("/Swing.png"));
        swingView = new ImageView(slide);
        swingView.setFitHeight(200);
        swingView.setFitWidth(150);

        swingView.setLayoutX(x);
        swingView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(swingView);
    }

    public ImageView getSwingView(){
        return swingView;
    }
}
