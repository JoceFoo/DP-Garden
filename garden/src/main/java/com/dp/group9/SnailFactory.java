package com.dp.group9;

public class SnailFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Snail();
    }
}