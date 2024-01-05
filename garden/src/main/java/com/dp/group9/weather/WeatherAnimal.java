package com.dp.group9.weather;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherAnimal implements Observer {
    private String weatherAnimalName;
    private ImageView weatherAnimalView;
    private WeatherData weatherData;
    private boolean isSunnyWeather;
    private boolean isRainyWeather;
    private boolean isSnowyWeather;
    private boolean isWindyWeather;
    private boolean isStormyWeather;

    public WeatherAnimal(String weatherAnimalName, WeatherData weatherData) {
        this.weatherAnimalName = weatherAnimalName;
        this.weatherAnimalView = new ImageView();
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public ImageView getWeatherAnimalView() {
        return weatherAnimalView;
    }

    public void setWeatherAnimalView(ImageView weatherAnimalView) {
        this.weatherAnimalView = weatherAnimalView;
    }

    private void setImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                weatherAnimalView.setImage(image);
            } catch (Exception e) {
                // Handle image loading error
                e.printStackTrace();
            }
        } else {
            // Set to null or an empty image
            weatherAnimalView.setImage(null);
        }
    }

    private String getImagePath() {
        // Define the image paths based on the weather conditions
        if (isSunnyWeather) {
            return "WeatherAnimalPhoto/sunnyDog.png";
        } else if (isRainyWeather) {
            return "WeatherAnimalPhoto/rainyCat.png";
        } else if (isSnowyWeather) {
            return "WeatherAnimalPhoto/snowyDeer.png";
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
        if (weatherData.getObservers().contains(this)) {
            isSunnyWeather = weather.equals("Sunny");
            isRainyWeather = weather.equals("Rainy");
            isSnowyWeather = weather.equals("Snowy");
            isWindyWeather = weather.equals("Windy");
            isStormyWeather = weather.equals("Stormy");
            placeAnimal();
        } else {
            isSunnyWeather = false;
            isRainyWeather = false;
            isSnowyWeather = false;
            isWindyWeather = false;
            isStormyWeather = false;
            setImage(null);
        }
    }

    public void placeAnimal() {
        // Define the position where you want to place the animal based on the weather
        double x;
        double y;
        double width = 200;
        double height = 200;
        if (isSunnyWeather) {
            x = 400;
            y = 500;
        } else if (isRainyWeather) {
            x = 300;
            y = 400;
        } else if (isSnowyWeather) {
            x = 550;
            y = 330;
            width = 180;
            height = 180;
        } else if (isWindyWeather) {
            x = 400;
            y = 500;
        } else if (isStormyWeather) {
            x = 530;
            y = 350;
            width = 180;
            height = 180;
        } else {
            // Default for sunny or unknown weather
            x = 100;
            y = 500;
        }

        // Set the position of the animal
        weatherAnimalView.setLayoutX(x);
        weatherAnimalView.setLayoutY(y);
        weatherAnimalView.setFitWidth(width);
        weatherAnimalView.setFitHeight(height);

        // Load the appropriate image based on the weather
        String imagePath = getImagePath();
        // System.out.println(imagePath);
        setImage(imagePath);
    }
}
