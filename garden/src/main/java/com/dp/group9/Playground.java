package com.dp.group9;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Playground extends Facility{
    private ArrayList<Facility> facilities;
    private ImageView image;

    public Playground(){
        facilities = new ArrayList<Facility>();
    }

    public void addFacility(Facility facility){
        facilities.add(facility);
    }

    public void addFacilities(Facility... facilities){
        for (Facility facility : facilities) {
            this.facilities.add(facility);
        }
    }

    public void removeFacilities(Facility... facilities){
        for (Facility facility : facilities) {
            this.facilities.remove(facility);
        }
    }

    public void display(){
        for (Facility facility : facilities) {
            facility.display();
        }
    }

    @Override
    public ImageView getView() {
        return image;
    }
}
