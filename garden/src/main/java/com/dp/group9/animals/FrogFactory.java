package com.dp.group9.animals;

public class FrogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Frog();
    }
}
