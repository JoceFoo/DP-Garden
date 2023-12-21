package com.dp.group9;

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
        slideView.setFitHeight(200);
        slideView.setFitWidth(200);

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
