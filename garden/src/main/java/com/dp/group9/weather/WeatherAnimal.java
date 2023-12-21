package com.dp.group9.weather;

public class WeatherAnimal implements Observer {
    private String weatherAnimalName;

    public WeatherAnimal(String weatherAnimalName) {
        this.weatherAnimalName = weatherAnimalName;
    }

    @Override
    public void update(String weather) {
        System.out.println(weatherAnimalName + " plant: Weather changed to " + weather);
        switch (weather.toLowerCase()) {
            case "sunny":
                System.out.println("Watering " + weatherAnimalName + " plant");
                break;
            case "rainy":
                System.out.println(weatherAnimalName + " is happy");
                break;
            case "snowy":
                System.out.println(weatherAnimalName + " Dies");
                break;
            case "Strong Wind":
                System.out.println(weatherAnimalName + " is destroyed");
                break;
            case "stormy":
                System.out.println(weatherAnimalName + " is destroyed");
                break;
            default:
                System.out.println("Unknown weather condition");
        }
    }
}
