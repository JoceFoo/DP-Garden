package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ClimbingWalls extends Facility{

    Pane pane;
    private ImageView climbingWallsView;

    public ClimbingWalls(Pane pane, double x, double y){
        this.pane = pane;
        Image climbingWalls = new Image(getClass().getResourceAsStream("/ClimbingWalls.png"));
        climbingWallsView = new ImageView(climbingWalls);
        climbingWallsView.setFitHeight(380);
        climbingWallsView.setFitWidth(400);

        climbingWallsView.setLayoutX(x);
        climbingWallsView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(climbingWallsView);
    }    
    
    public ImageView getView(){
        return climbingWallsView;
    }
}
