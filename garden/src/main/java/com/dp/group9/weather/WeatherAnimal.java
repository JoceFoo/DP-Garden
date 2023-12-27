package com.dp.group9.weather;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherAnimal implements Observer {
    private String weatherAnimalName;
    private ImageView weatherAnimalView;
    private boolean isSunnyWeather;
    private boolean isRainyWeather;
    private boolean isSnowyWeather;
    private boolean isWindyWeather;
    private boolean isStormyWeather;

    public WeatherAnimal(String weatherAnimalName) {
        this.weatherAnimalName = weatherAnimalName;
        this.weatherAnimalView = new ImageView();
    }

    public ImageView getWeatherAnimalView() {
        return weatherAnimalView;
    }

    public void setWeatherAnimalView(ImageView weatherAnimalView) {
        this.weatherAnimalView = weatherAnimalView;
    }

    private void setImage(String imagePath) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            weatherAnimalView.setImage(image);
        } catch (Exception e) {
            // Handle image loading error
            e.printStackTrace();
        }
    }

    private String getImagePath() {
        // Define the image paths based on the weather conditions
        if (isSunnyWeather) {
            return "WeatherAnimalPhoto/sunnyDog.png";
        } else if (isRainyWeather) {
            return "WeatherAnimalPhoto/rainyCat.png";
        } else if (isSnowyWeather) {
            return "WeatherAnimalPhoto/snowyPenguin.png";
        } else if (isWindyWeather) {
            return "WeatherAnimalPhoto/windyPooh.png";
        } else if (isStormyWeather) {
            return "WeatherAnimalPhoto/stormyPanda.png";
        } else {
            // Default for sunny or unknown weather
            return "WeatherAnimalPhoto/sunnyDog.png";
        }
    }

    @Override
    public void update(String weather) {
        isSunnyWeather = weather.equals("Sunny");
        isRainyWeather = weather.equals("Rainy");
        isSnowyWeather = weather.equals("Snowy");
        isWindyWeather = weather.equals("Windy");
        isStormyWeather = weather.equals("Stormy");
        placeAnimal();
    }

    public void placeAnimal() {
        // Define the position where you want to place the animal based on the weather
        double x;
        double y;
        if (isSunnyWeather) {
            x = 400;
            y = 500;
        } else if (isRainyWeather) {
            x = 300;
            y = 400;
        } else if (isSnowyWeather) {
            x = 580;
            y = 330;
        } else if (isWindyWeather) {
            x = 400;
            y = 500;
        } else if (isStormyWeather) {
            x = 530;
            y = 330;
        } else {
            // Default for sunny or unknown weather
            x = 100;
            y = 500;
        }

        // Set the position of the animal
        weatherAnimalView.setLayoutX(x);
        weatherAnimalView.setLayoutY(y);
        weatherAnimalView.setFitWidth(200);
        weatherAnimalView.setFitHeight(200);

        // Load the appropriate image based on the weather
        String imagePath = getImagePath();
        // System.out.println(imagePath);
        setImage(imagePath);
    }
}
