package com.dp.group9;

import java.util.ArrayList;

public class Playground implements Facility{
    private ArrayList<Facility> facilities;

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

    public void display(){
        for (Facility facility : facilities) {
            facility.display();
        }
    }
}
