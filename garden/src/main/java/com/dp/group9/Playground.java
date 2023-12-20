package com.dp.group9;

import java.util.List;

public class Playground implements Facility{
    private List<Facility> facilities;

    public Playground(List<Facility> facilities){
        this.facilities = facilities;
    }

    public void addFacilities(Facility facility){
        facilities.add(facility);
    }
    public void display(){
        for (Facility facility : facilities) {
            facility.display();
        }
    }
}
