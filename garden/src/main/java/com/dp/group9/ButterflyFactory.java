package com.dp.group9;

public class ButterflyFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Butterfly();
    }
}
