package com.dp.group9;

public abstract class PlantDecorator extends Plant{
    Plant plant;

    public PlantDecorator(Plant plant){
        this.plant = plant;
    }

    public void display(){
        plant.display();
    }
}
