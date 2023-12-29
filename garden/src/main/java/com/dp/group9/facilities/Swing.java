package com.dp.group9.facilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Swing extends Facility{

    Pane pane;
    private ImageView swingView;

    public Swing(Pane pane, double x, double y){
        this.pane = pane;
        Image slide = new Image(getClass().getResourceAsStream("/Swing.png"));
        swingView = new ImageView(slide);
        swingView.setFitHeight(170);
        swingView.setFitWidth(120);

        swingView.setLayoutX(x);
        swingView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(swingView);
    }

    public ImageView getView(){
        return swingView;
    }
}
