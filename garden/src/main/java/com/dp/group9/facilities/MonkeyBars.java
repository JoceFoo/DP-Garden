package com.dp.group9.facilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MonkeyBars extends Facility{

    Pane pane;
    private ImageView monkeyBarsView;

    public MonkeyBars(Pane pane, double x, double y){
        this.pane = pane;
        Image monkeyBars = new Image(getClass().getResourceAsStream("/MonkeyBars.png"));
        monkeyBarsView = new ImageView(monkeyBars);
        monkeyBarsView.setFitHeight(320);
        monkeyBarsView.setFitWidth(320);

        monkeyBarsView.setLayoutX(x);
        monkeyBarsView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(monkeyBarsView);
    }    
    
    public ImageView getView(){
        return monkeyBarsView;
    }
}
