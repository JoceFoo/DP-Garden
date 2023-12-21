package com.dp.group9;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.dp.group9.weather.Observer;
import com.dp.group9.weather.WeatherData;
import com.dp.group9.weather.WeatherPlant;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Group 9
 * Virtual Space: Garden
 */
public class App extends Application implements Observer {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_DROPS = 100;
    private WeatherData weatherData = new WeatherData();
    // Create an instance of WeatherPlant for a specific plant
    WeatherPlant Sunflower = new WeatherPlant("Sunflower"); // Change the name as needed

    private List<Droplet> droplets = new ArrayList<>();
    private List<Snowflake> snowflakes = new ArrayList<>();
    private List<Leaf> leaves = new ArrayList<>();
    private List<Lightning> lightningFlashes = new ArrayList<>();

    // private static class Droplet {
    // double x;
    // double y;
    // double speed;

    // public Droplet(double x, double y, double speed) {
    // this.x = x;
    // this.y = y;
    // this.speed = speed;
    // }

    // public void fall() {
    // y += speed;
    // if (y > HEIGHT) {
    // y = 0;
    // x = new Random().nextDouble() * WIDTH;
    // }
    // }
    // }

    // private static class Snowflake {
    // double x;
    // double y;
    // double speed;

    // public Snowflake(double x, double y, double speed) {
    // this.x = x;
    // this.y = y;
    // this.speed = speed;
    // }

    // public void fall() {
    // y += speed;
    // if (y > HEIGHT) {
    // y = 0;
    // x = new Random().nextDouble() * WIDTH;
    // }
    // }
    // }

    // private static class Leaf {
    // double x;
    // double y;
    // double speed;

    // public Leaf(double x, double y, double speed) {
    // this.x = x;
    // this.y = y;
    // this.speed = speed;
    // }

    // public void blow() {
    // x += speed;
    // if (x > WIDTH) {
    // x = 0;
    // }
    // }
    // }

    // private static class Lightning {
    // double startX;
    // double startY;
    // double endX;
    // double endY;
    // boolean isVisible;

    // public Lightning(double startX, double startY, double endX, double endY) {
    // this.startX = startX;
    // this.startY = startY;
    // this.endX = endX;
    // this.endY = endY;
    // this.isVisible = false;
    // }

    // public void flash() {
    // isVisible = !isVisible;
    // }
    // }
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

    private GraphicsContext gc;
    private boolean isRainyWeather = false;
    private boolean isSnowyWeather = false;
    private boolean isWindyWeather = false;
    private boolean isStormyWeather = false;

    @Override
    public void start(Stage primaryStage) {
        // WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(this);
        weatherData.registerObserver(Sunflower);
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        initializeWeatherElements();
        // Load background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("Fourth.jpg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(WIDTH);
        backgroundImageView.setFitHeight(HEIGHT);
        // imageUrl not found

        StackPane root = new StackPane(backgroundImageView, canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("Rain Background");
        primaryStage.setScene(scene);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
        // Add a VBox to contain buttons
        VBox buttonContainer = new VBox(10);
        buttonContainer.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().add(buttonContainer);

        // Button to choose weather
        Button chooseWeatherButton = new Button("Choose Weather");
        chooseWeatherButton.setOnAction(e -> showWeatherDialog());
        buttonContainer.getChildren().add(chooseWeatherButton);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Clear canvas
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                // Draw raindrops only if it's rainy weather
                if (isRainyWeather) {
                    gc.setFill(Color.BLUE);
                    for (Droplet droplet : droplets) {
                        droplet.fall();
                        gc.fillOval(droplet.x, droplet.y, 2, 8);
                    }
                }

                else if (isSnowyWeather) {
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
                    // Draw heavy rain
                    for (Droplet droplet : droplets) {
                        droplet.fall();
                        gc.fillOval(droplet.x, droplet.y, 2, 8);
                    }

                    // Simulate lightning flashes
                    if (Math.random() < 0.03) {
                        for (Lightning lightning : lightningFlashes) {
                            lightning.flash();
                            if (lightning.isVisible) {
                                gc.setStroke(Color.WHITE);
                                gc.strokeLine(lightning.startX, lightning.startY, lightning.endX, lightning.endY);
                            }
                        }
                    }
                } else {
                    gc.clearRect(0, 0, WIDTH, HEIGHT);

                }
            }
        };
        animationTimer.start();
    }

    private void initializeWeatherElements() {
        for (int i = 0; i < NUM_DROPS; i++) {
            droplets.add(new Droplet());
            snowflakes.add(new Snowflake());
            leaves.add(new Leaf());
            lightningFlashes.add(new Lightning());
        }
    }

    private void showWeatherDialog() {
        // WeatherData weatherData = new WeatherData();

        ChoiceDialog<String> weatherDialog = new ChoiceDialog<>("Sunny", "Sunny", "Rainy", "Snowy", "Windy", "Stormy");
        weatherDialog.setTitle("Select Weather");
        weatherDialog.setHeaderText(null);
        weatherDialog.setContentText("Choose the current weather:");

        Optional<String> selectedWeather = weatherDialog.showAndWait();
        if (selectedWeather.isPresent()) {
            String weather = selectedWeather.get();
            weatherData.setWeather(weather);
            Sunflower.update(weather);

            // String weather = selectedWeather.get();
            // weatherData.setWeather(weather);
            // update(weather);
            isRainyWeather = weather.equals("Rainy");
            isSnowyWeather = weather.equals("Snowy");
            isWindyWeather = weather.equals("Windy");
            isStormyWeather = weather.equals("Stormy");
            // Sunflower.update(weather);
        }
    }

    public void displayWeatherConfirmation(String weather) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Weather Set");
        alert.setHeaderText(null);
        alert.setContentText("The current weather is set to: " + weather);

        alert.showAndWait();
    }

    @Override
    public void update(String weather) {
        // isRainyWeather = weather.equals("Rainy");
        displayWeatherConfirmation(weather);
    }

    private void addTree(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Tree tree = new Tree(plant, pane);
        double start = pane.getWidth() / 3;
        double end = 2 * start;

        double randomX;
        if (random.nextBoolean()) {
            randomX = random.nextDouble() * (start - tree.getTreeView().getFitWidth() / 2);
        } else {
            randomX = end + random.nextDouble() * (start - tree.getTreeView().getFitWidth() / 2);
        }

        int minY = 350;
        int maxY = 400;
        int randomY = random.nextInt(maxY - minY) + minY;

        tree.getTreeView().setLayoutX(randomX);
        tree.getTreeView().setLayoutY(randomY);

        tree.display();
    }

    private void addFlower(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Flower flower = new Flower(plant, pane);

        flower.getFlowerView()
                .setLayoutX(random.nextDouble() * (pane.getWidth() - flower.getFlowerView().getFitWidth()));
        flower.getFlowerView().setLayoutY(pane.getHeight() - flower.getFlowerView().getFitHeight());

        flower.display();
    }

    private void addGrass(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Grass grass = new Grass(plant, pane);

        grass.getGrassView().setLayoutX(random.nextDouble() * (pane.getWidth() - grass.getGrassView().getFitWidth()));
        grass.getGrassView().setLayoutY(pane.getHeight() - grass.getGrassView().getFitHeight());

        grass.display();
    }

    public static void main(String[] args) {
        launch(args);
    }

}