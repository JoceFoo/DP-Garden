package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class JungleGym implements Facility{

    Pane pane;
    private ImageView jungleGymView;

    public JungleGym(Pane pane, double x, double y){
        this.pane = pane;
        Image jungleGym = new Image(getClass().getResourceAsStream("/JungleGym.png"));
        jungleGymView = new ImageView(jungleGym);
        jungleGymView.setFitHeight(220);
        jungleGymView.setFitWidth(220);

        jungleGymView.setLayoutX(x);
        jungleGymView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(jungleGymView);
    }    
    
    public ImageView getJungleGymView(){
        return jungleGymView;
    }
}
