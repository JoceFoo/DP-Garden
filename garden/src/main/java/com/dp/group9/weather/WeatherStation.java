// File: WeatherController.java
package com.dp.group9.weather;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation implements Observer {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_DROPS = 100;

    private List<Droplet> droplets = new ArrayList<>();
    private List<Snowflake> snowflakes = new ArrayList<>();
    private List<Leaf> leaves = new ArrayList<>();
    private List<Lightning> lightningFlashes = new ArrayList<>();
    private GraphicsContext gc;
    private boolean isRainyWeather;
    private boolean isSnowyWeather;
    private boolean isWindyWeather;
    private boolean isStormyWeather;

    public WeatherStation(GraphicsContext gc, WeatherData weatherData) {
        this.gc = gc;
        weatherData.registerObserver(this);
        initializeWeatherElements();
    }
    // public WeatherStation(GraphicsContext gc, WeatherData weatherData) {
    // this.gc = gc;
    // weatherData.registerObserver(this);
    // initializeWeatherElements();
    // }

    @Override
    public void update(String weather) {
        isRainyWeather = weather.equals("Rainy");
        isSnowyWeather = weather.equals("Snowy");
        isWindyWeather = weather.equals("Windy");
        isStormyWeather = weather.equals("Stormy");
    }

    private static class Droplet {
        double x;
        double y;
        double speed;

        public Droplet() {
            this.x = new Random().nextDouble() * WIDTH;
            this.y = new Random().nextDouble() * HEIGHT;
            this.speed = 5 + Math.random() * 5;
        }

        public void fall() {
            y += speed;
            if (y > HEIGHT) {
                y = 0;
                x = new Random().nextDouble() * WIDTH;
            }
        }
    }

    private static class Snowflake {
        double x;
        double y;
        double speed;

        public Snowflake() {
            this.x = new Random().nextDouble() * WIDTH;
            this.y = new Random().nextDouble() * HEIGHT;
            this.speed = 2 + Math.random() * 3;
        }

        public void fall() {
            y += speed;
            if (y > HEIGHT) {
                y = 0;
                x = new Random().nextDouble() * WIDTH;
            }
        }
    }

    private static class Leaf {
        double x;
        double y;
        double speed;

        public Leaf() {
            this.x = new Random().nextDouble() * WIDTH;
            this.y = new Random().nextDouble() * HEIGHT;
            this.speed = 2 + Math.random() * 3;
        }

        public void blow() {
            x += speed;
            if (x > WIDTH) {
                x = 0;
            }
        }
    }

    private static class Lightning {
        double startX;
        double startY;
        double endX;
        double endY;
        boolean isVisible;

        public Lightning() {
            this.startX = new Random().nextDouble() * WIDTH;
            this.startY = 0;
            this.endX = new Random().nextDouble() * WIDTH;
            this.endY = HEIGHT;
            this.isVisible = false;
        }

        public void flash() {
            isVisible = !isVisible;
        }
    }

    private void initializeWeatherElements() {
        for (int i = 0; i < NUM_DROPS; i++) {
            droplets.add(new Droplet());
            snowflakes.add(new Snowflake());
            leaves.add(new Leaf());
            lightningFlashes.add(new Lightning());
        }
    }

    public void drawWeather() {
        // Clear canvas
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Draw weather based on current conditions
        if (isRainyWeather) {
            gc.setFill(Color.BLUE);
            for (Droplet droplet : droplets) {
                droplet.fall();
                gc.fillOval(droplet.x, droplet.y, 2, 8);
            }
        } else if (isSnowyWeather) {
            gc.setFill(Color.WHITE);
            for (Snowflake snowflake : snowflakes) {
                snowflake.fall();
                gc.fillOval(snowflake.x, snowflake.y, 5, 5);
            }
        } else if (isWindyWeather) {
            gc.setFill(Color.GREEN);
            for (Leaf leaf : leaves) {
                leaf.blow();
                gc.fillOval(leaf.x, leaf.y, 5, 5);
            }
        } else if (isStormyWeather) {
            gc.setFill(Color.DARKGRAY);

            for (Droplet droplet : droplets) {
                droplet.fall();
                gc.fillOval(droplet.x, droplet.y, 2, 8);
            }

            if (Math.random() < 0.03) {
                for (Lightning lightning : lightningFlashes) {
                    lightning.flash();
                    if (lightning.isVisible) {
                        gc.setStroke(Color.WHITE);
                        gc.strokeLine(lightning.startX, lightning.startY, lightning.endX, lightning.endY);
                    }
                }
            }
        }
    }
}
