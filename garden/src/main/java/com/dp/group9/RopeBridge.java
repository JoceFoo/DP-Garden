package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RopeBridge implements Facility{

    Pane pane;
    private ImageView ropeBridgeView;

    public RopeBridge(Pane pane, double x, double y){
        this.pane = pane;
        Image ropeBridge = new Image(getClass().getResourceAsStream("/RopeBridge.png"));
        ropeBridgeView = new ImageView(ropeBridge);
        ropeBridgeView.setFitHeight(250);
        ropeBridgeView.setFitWidth(250);

        ropeBridgeView.setLayoutX(x);
        ropeBridgeView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(ropeBridgeView);
    }    
    
    public ImageView getRopeBridgeView(){
        return ropeBridgeView;
    }
}
