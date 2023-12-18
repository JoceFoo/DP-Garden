package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RopeNetClimbing implements Facility{

    Pane pane;
    private ImageView ropeNetClimbingView;

    public RopeNetClimbing(Pane pane, double x, double y){
        this.pane = pane;
        Image ropeNetClimbing = new Image(getClass().getResourceAsStream("/RopeNetString.png"));
        ropeNetClimbingView = new ImageView(ropeNetClimbing);
        ropeNetClimbingView.setFitHeight(170);
        ropeNetClimbingView.setFitWidth(170);

        ropeNetClimbingView.setLayoutX(x);
        ropeNetClimbingView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(ropeNetClimbingView);
    }    
    
    public ImageView getRopeNetClimbingView(){
        return ropeNetClimbingView;
    }
}
