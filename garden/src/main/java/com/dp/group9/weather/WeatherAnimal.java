package com.dp.group9.weather;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherAnimal implements Observer {
    private String weatherAnimalName;
    private ImageView weatherAnimalView;

    public WeatherAnimal(String weatherAnimalName) {
        this.weatherAnimalName = weatherAnimalName;
        this.weatherAnimalView = new ImageView(); // You may want to set a default image
    }

    public ImageView getWeatherAnimalView() {
        return weatherAnimalView;
    }

    public void setWeatherAnimal(String weatherAnimalType) {
        // Load the appropriate image based on the weather animal type
        String imagePath = getImagePath(weatherAnimalType);
        Image image = new Image(imagePath);
        weatherAnimalView.setImage(image);
    }

    private String getImagePath(String weather) {
        // Define the image paths for different weather animal types
        switch (weather.toLowerCase()) {
            case "sunny":
                return "sunnyDog.jpg";
            case "rainy":
                return "sunnyDog.jpg";
            case "snowy":
                return "sunnyDog.jpg";
            case "windy":
                return "sunnyDog.jpg";
            case "stormy":
                return "sunnyDog.jpg";
            default:
                return "sunnyDog.jpg";
        }
    }

    @Override
    public void update(String weather) {
        // Use the update function to place the animal based on the weather
        placeAnimal(weather);
    }

    private void placeAnimal(String weather) {
        // Define the position where you want to place the animal based on the weather
        double x = 0;
        double y = 0;

        // Set the position based on the weather type
        switch (weather.toLowerCase()) {
            case "sunny":
                x = 100;
                y = 500;
                break;
            case "rainy":
                x = 300;
                y = 200;
                break;
            case "snowy":
                x = 600;
                y = 300;
                break;
            case "windy":
                x = 800;
                y = 100;
                break;
            case "stormy":
                x = 400;
                y = 400;
                break;
            default:
                // Set a default position if the weather is unknown
                x = 100;
                y = 100;
        }

        // Set the position of the animal
        weatherAnimalView.setLayoutX(x);
        weatherAnimalView.setLayoutY(y);

        // Load the appropriate image based on the weather
        String imagePath = getImagePath(weather);
        System.out.println(imagePath);
        Image image = new Image(imagePath);
        weatherAnimalView.setImage(image);
    }
    // @Override
    // public void update(String weather) {
    // // Use the update function to return the image path based on the selected
    // // weather animal type
    // String imagePath = getImagePath(weather);
    // Image image = new Image(imagePath);
    // weatherAnimalView.setImage(image);
    // }
    // @Override
    // public void update(String weather) {
    // System.out.println(weatherAnimalName + " plant: Weather changed to " +
    // weather);
    // switch (weather.toLowerCase()) {
    // case "sunny":
    // System.out.println("Watering " + weatherAnimalName + " plant");
    // break;
    // case "rainy":
    // System.out.println(weatherAnimalName + " is happy");
    // break;
    // case "snowy":
    // System.out.println(weatherAnimalName + " Dies");
    // break;
    // case "Strong Wind":
    // System.out.println(weatherAnimalName + " is destroyed");
    // break;
    // case "stormy":
    // System.out.println(weatherAnimalName + " is destroyed");
    // break;
    // default:
    // System.out.println("Unknown weather condition");
    // }
    // }
}
