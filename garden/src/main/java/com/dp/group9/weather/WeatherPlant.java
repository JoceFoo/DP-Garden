package com.dp.group9.weather;

public class WeatherPlant implements Observer {
    private String weatherPlantName;

    public WeatherPlant(String weatherPlantName) {
        this.weatherPlantName = weatherPlantName;
    }

    @Override
    public void update(String weather) {
        System.out.println(weatherPlantName + " plant: Weather changed to " + weather);
        switch (weather.toLowerCase()) {
            case "sunny":
                System.out.println("Watering " + weatherPlantName + " plant");
                break;
            case "rainy":
                System.out.println("Watering " + weatherPlantName + " plant");
                break;
            case "snowy":
                System.out.println("Watering " + weatherPlantName + " plant");
                break;
            case "Strong Wind":
                System.out.println("Watering " + weatherPlantName + " plant");
                break;
            case "stormy":
                System.out.println("Watering " + weatherPlantName + " plant");
                break;
            default:
                System.out.println("Unknown weather condition");
        }
    }

}
