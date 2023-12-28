package com.dp.group9.animals;

public class SnailFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Snail();
    }
}