package com.dp.group9.weather;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherPlant implements Observer {
    private String weatherPlantName;
    private ImageView weatherPlantView;
    private boolean isSunnyWeather;
    private boolean isRainyWeather;
    private boolean isSnowyWeather;
    private boolean isWindyWeather;
    private boolean isStormyWeather;
    // Add a default image path
    private static final String DEFAULT_IMAGE_PATH = "WeatherPlantPhoto/sunFlower.png";

    public WeatherPlant(String weatherPlantName) {
        this.weatherPlantName = weatherPlantName;
        this.weatherPlantView = new ImageView();
        // setDefaultImage();
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
            return "WeatherPlantPhoto/sunFlower.png";
        } else if (isSnowyWeather) {
            return "WeatherPlantPhoto/sunFlower.png";
        } else if (isWindyWeather) {
            return "WeatherPlantPhoto/sunFlower.png";
        } else if (isStormyWeather) {
            return "WeatherPlantPhoto/sunFlower.png";
        } else {
            // Default for sunny or unknown weather
            return "WeatherPlantPhoto/sunFlower.png";
        }
    }

    @Override
    public void update(String weather) {
        isSunnyWeather = weather.equals("Sunny");
        isRainyWeather = weather.equals("Rainy");
        isSnowyWeather = weather.equals("Snowy");
        isWindyWeather = weather.equals("Windy");
        isStormyWeather = weather.equals("Stormy");
        placePlant();
    }

    public void placePlant() {
        // Define the position where you want to place the Plant based on the weather
        double x;
        double y;
        if (isSunnyWeather) {
            x = 200;
            y = 500;
        } else if (isRainyWeather) {
            x = 350;
            y = 200;
        } else if (isSnowyWeather) {
            x = 200;
            y = 200;
        } else if (isWindyWeather) {
            x = 300;
            y = 300;
        } else if (isStormyWeather) {
            x = 400;
            y = 400;
        } else {
            // Default for sunny or unknown weather
            x = 500;
            y = 500;
        }

        // Set the position of the Plant
        weatherPlantView.setLayoutX(x);
        weatherPlantView.setLayoutY(y);
        weatherPlantView.setFitWidth(200);
        weatherPlantView.setFitHeight(200);

        // Load the appropriate image based on the weather
        String imagePath = getImagePath();
        // System.out.println(imagePath);
        setImage(imagePath);
    }
}
