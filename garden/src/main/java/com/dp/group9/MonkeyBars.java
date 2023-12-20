package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MonkeyBars implements Facility{

    Pane pane;
    private ImageView monkeyBarsView;

    public MonkeyBars(Pane pane, double x, double y){
        this.pane = pane;
        Image monkeyBars = new Image(getClass().getResourceAsStream("/MonkeyBars.png"));
        monkeyBarsView = new ImageView(monkeyBars);
        monkeyBarsView.setFitHeight(290);
        monkeyBarsView.setFitWidth(290);

        monkeyBarsView.setLayoutX(x);
        monkeyBarsView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(monkeyBarsView);
    }    
    
    public ImageView getMonkeyBarsView(){
        return monkeyBarsView;
    }
}