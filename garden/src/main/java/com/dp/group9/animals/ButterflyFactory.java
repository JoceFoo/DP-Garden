package com.dp.group9.animals;

public class ButterflyFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Butterfly();
    }
}
