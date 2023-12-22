package com.dp.group9;

public class FrogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Frog();
    }
}
