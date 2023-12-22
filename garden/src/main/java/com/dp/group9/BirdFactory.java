package com.dp.group9;

public class BirdFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Bird();
    }
}
