package com.dp.group9.animals;

public class BirdFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Bird();
    }
}
