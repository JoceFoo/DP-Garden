package com.dp.group9.plants;

import javafx.scene.image.ImageView;

public abstract class PlantDecorator extends Plant{
    Plant plant;
    ImageView plantView;

    public PlantDecorator(Plant plant){
        this.plant = plant;
    }

    public ImageView getView(){
        return plantView;
    }

    public void display(){
        plant.display();
    }
}
