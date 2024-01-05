package com.dp.group9.weather;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherPlant implements Observer {
    private String weatherPlantName;
    private ImageView weatherPlantView;
    private WeatherData weatherData;
    private boolean isSunnyWeather;
    private boolean isRainyWeather;
    private boolean isSnowyWeather;
    private boolean isWindyWeather;
    private boolean isStormyWeather;

    public WeatherPlant(String weatherPlantName, WeatherData weatherData) {
        this.weatherPlantName = weatherPlantName;
        this.weatherPlantView = new ImageView();
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public ImageView getWeatherPlantView() {
        return weatherPlantView;
    }

    public void setWeatherPlantView(ImageView weatherPlantView) {
        this.weatherPlantView = weatherPlantView;
    }

    private void setImage(String imagePath) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            weatherPlantView.setImage(image);
        } catch (Exception e) {
            // Handle image loading error
            e.printStackTrace();
        }
    }

    private String getImagePath() {
        // Define the image paths based on the weather conditions
        if (isSunnyWeather) {
            return "WeatherPlantPhoto/sunFlower.png";
        } else if (isRainyWeather) {
            return "WeatherPlantPhoto/rainPlant.png";
        } else if (isSnowyWeather) {
            return "WeatherPlantPhoto/snowPlant.png";
        } else if (isWindyWeather) {
            return "WeatherPlantPhoto/windPlant.png";
        } else if (isStormyWeather) {
            return "WeatherPlantPhoto/stormPlant.png";
        } else {
            // Default for sunny or unknown weather
            return "WeatherPlantPhoto/sunFlower.png";
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
            placePlant();
        } else {
            isSunnyWeather = false;
            isRainyWeather = false;
            isSnowyWeather = false;
            isWindyWeather = false;
            isStormyWeather = false;
        }
    }

    public void placePlant() {
        // Define the position where you want to place the Plant based on the weather
        double x;
        double y;
        if (isSunnyWeather && isRainyWeather && isSnowyWeather && isWindyWeather) {
            x = 700;
            y = 450;
        } else if (isSnowyWeather) {
            x = 440;
            y = 480;
        } else if (isStormyWeather) {
            x = 550;
            y = 200;
        } else {
            // Default for sunny or unknown weather
            x = 500;
            y = 500;
        }

        // Set the position of the Plant
        weatherPlantView.setLayoutX(x);
        weatherPlantView.setLayoutY(y);
        weatherPlantView.setFitWidth(200);
        weatherPlantView.setFitHeight(300);

        // Load the appropriate image based on the weather
        String imagePath = getImagePath();
        // System.out.println(imagePath);
        setImage(imagePath);
    }
}
