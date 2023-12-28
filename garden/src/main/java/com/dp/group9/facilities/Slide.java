package com.dp.group9.facilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Slide extends Facility{

    Pane pane;
    private ImageView slideView;

    public Slide(Pane pane, double x, double y){
        this.pane = pane;
        Image slide = new Image(getClass().getResourceAsStream("/Slide.png"));
        slideView = new ImageView(slide);
        slideView.setFitHeight(250);
        slideView.setFitWidth(250);

        slideView.setLayoutX(x);
        slideView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(slideView);
    }

    public ImageView getView(){
        return slideView;
    }
}
