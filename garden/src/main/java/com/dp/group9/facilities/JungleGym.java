package com.dp.group9.facilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class JungleGym extends Facility{

    Pane pane;
    private ImageView jungleGymView;

    public JungleGym(Pane pane, double x, double y){
        this.pane = pane;
        Image jungleGym = new Image(getClass().getResourceAsStream("/JungleGym.png"));
        jungleGymView = new ImageView(jungleGym);
        jungleGymView.setFitHeight(230);
        jungleGymView.setFitWidth(230);

        jungleGymView.setLayoutX(x);
        jungleGymView.setLayoutY(y);
    }

    public void display(){
        pane.getChildren().add(jungleGymView);
    }    
    
    public ImageView getView(){
        return jungleGymView;
    }
}
