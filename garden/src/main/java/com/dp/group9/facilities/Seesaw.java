package com.dp.group9.facilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Seesaw extends Facility{

    Pane pane;
    private ImageView seesawView;

    public Seesaw(Pane pane, double x, double y){
        this.pane = pane;
        Image seesaw = new Image(getClass().getResourceAsStream("/Seesaw.png"));
        seesawView = new ImageView(seesaw);
        seesawView.setFitHeight(150);
        seesawView.setFitWidth(150);

        seesawView.setLayoutX(x);
        seesawView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(seesawView);
    }    
    
    public ImageView getView(){
        return seesawView;
    }
}
