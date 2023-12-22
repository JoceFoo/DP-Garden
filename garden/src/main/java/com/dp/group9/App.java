package com.dp.group9;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.dp.group9.weather.WeatherAnimal;
import com.dp.group9.weather.WeatherData;
import com.dp.group9.weather.WeatherPlant;
import com.dp.group9.weather.WeatherStation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Group 9
 * Virtual Space: Garden
 */
public class App extends Application {

    private WeatherData weatherData = new WeatherData();// Subject
    private WeatherStation weatherStation;// Observer

    WeatherPlant weatherPlant = new WeatherPlant("weatherPlant");// Observer
    WeatherAnimal weatherAnimal = new WeatherAnimal("weatherAnimal");// Observer
    Canvas canvas = new Canvas(1000, 750);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    private String lastSelectedWeather = "No Weather Selected";

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, 1000, 750); // maintain 4:3 (width to height) ratio
        GardenLayout gardenLayout = GardenLayout.getInstance();
        gardenLayout.setLayout(LayoutType.Garden.getLayoutName(), root);
        weatherData.registerObserver(weatherPlant);
        weatherData.registerObserver(weatherAnimal);
        weatherStation = new WeatherStation(gc, weatherData);

        // Add Playground
        addPlayground(gardenLayout.getLayoutName(), root);

        // Garden Layout
        MenuButton layoutButton = new MenuButton(gardenLayout.getLayoutName());
        layoutButton.setLayoutX(10);
        layoutButton.setLayoutY(10);
        layoutButton.setPrefWidth(130);
        for (LayoutType type : LayoutType.values()) {
            MenuItem menuItem = new MenuItem(type.getLayoutName());
            menuItem.setOnAction(e -> {
                gardenLayout.setLayout(type.getLayoutName(), root);
                layoutButton.setText(type.getLayoutName());
                root.getChildren().removeIf((node) -> !(node instanceof Button || node instanceof MenuButton));
                addPlayground(type.getLayoutName(), root);
            });
            layoutButton.getItems().add(menuItem);
        }

        // Tree
        Button treeButton = new Button("Add Tree");
        treeButton.setLayoutX(layoutButton.getLayoutX() + layoutButton.getWidth() + 140); // beside the layout button
        treeButton.setLayoutY(10);

        treeButton.setOnAction(e -> addTree(root));

        // Flower
        Button flowerButton = new Button("Add Flower");
        flowerButton.setLayoutX(treeButton.getLayoutX() + treeButton.getWidth() + 80); // beside the tree button
        flowerButton.setLayoutY(10);

        flowerButton.setOnAction(e -> addFlower(root));

        // Grass
        Button grassButton = new Button("Add Grass");
        grassButton.setLayoutX(flowerButton.getLayoutX() + flowerButton.getWidth() + 90);// beside the flower button
        grassButton.setLayoutY(10);
        grassButton.setOnAction(e -> addGrass(root));
        // Weather
        Button weatherButton = new Button("Choose Weather");
        weatherButton.setLayoutX(grassButton.getLayoutX() + grassButton.getWidth() + 90);// beside the grass button
        weatherButton.setLayoutY(10);
        weatherButton.setOnAction(e -> showWeatherDialog());

        // add Buttons and get+show scene
        root.getChildren().addAll(layoutButton, treeButton, flowerButton,
                grassButton, weatherButton, weatherPlant.getWeatherPlantView(), weatherAnimal.getWeatherAnimalView());

        stage.setTitle("Garden");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Clear canvas
                gc.clearRect(0, 0, 900, 720);
                weatherStation.drawWeather();
            }
        };
        animationTimer.start();
    }

    private void showWeatherDialog() {
        ChoiceDialog<String> weatherDialog = new ChoiceDialog<>(lastSelectedWeather, "Sunny", "Rainy", "Snowy", "Windy",
                "Stormy");
        weatherDialog.setTitle("Select Weather");
        weatherDialog.setHeaderText(null);
        weatherDialog.setContentText("Choose the current weather:");

        Optional<String> selectedWeather = weatherDialog.showAndWait();
        if (selectedWeather.isPresent()) {
            String weather = selectedWeather.get();
            lastSelectedWeather = weather;
            weatherData.setWeather(weather);
            weatherData.notifyObservers();
            weatherStation.update(weather);
            weatherAnimal.update(weather);
            weatherPlant.update(weather);
        }
    }

    public void displayWeatherConfirmation(String weather) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Weather Set");
        alert.setHeaderText(null);
        alert.setContentText("The current weather is set to: " + weather);

        alert.showAndWait();
    }

    private void addPlayground(String layoutType, Pane root) {
        Playground playground = new Playground();
        if (layoutType.equals(LayoutType.Garden.getLayoutName())) {
            // garden facilities
            Slide slide = new Slide(root, 80, 350);
            Seesaw seesaw = new Seesaw(root, 550, 270);
            playground.addFacilities(slide, seesaw);
        } else if (layoutType.equals(LayoutType.Hillside.getLayoutName())) {
            // hillside facilities
            Swing swing = new Swing(root, 700, 320);
            RopeNetClimbing ropeNetClimbing = new RopeNetClimbing(root, 80, 500);
            playground.addFacilities(swing, ropeNetClimbing);
        } else if (layoutType.equals(LayoutType.OffTheCity.getLayoutName())) {
            // off the city facilities
            ClimbingWalls climbingWalls = new ClimbingWalls(root, 600, 450);
            JungleGym jungleGym = new JungleGym(root, 250, 370);
            playground.addFacilities(climbingWalls, jungleGym);
        } else if (layoutType.equals(LayoutType.MountainView.getLayoutName())) {
            // mountain facilities
            MonkeyBars monkeyBars = new MonkeyBars(root, 70, 380);
            RopeBridge ropeBridge = new RopeBridge(root, 680, 300);
            playground.addFacilities(monkeyBars, ropeBridge);
        }
        playground.display();
    }

    private void addTree(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Tree tree = new Tree(plant, pane);
        double start = pane.getWidth() / 3;
        double end = 2 * start;

        double randomX;
        if (random.nextBoolean()) {
            randomX = random.nextDouble() * (start - tree.getView().getFitWidth() - 600);
        } else {
            randomX = end + random.nextDouble() * (start - tree.getView().getFitWidth() / 2);
        }

        int minY = 150;
        int maxY = 200;
        int randomY = random.nextInt(maxY - minY) + minY;

        tree.getView().setLayoutX(randomX);
        tree.getView().setLayoutY(randomY);

        tree.display();
    }

    private void addFlower(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Flower flower = new Flower(plant, pane);

        flower.getView().setLayoutX(random.nextDouble() * (pane.getWidth() - flower.getView().getFitWidth()));
        flower.getView().setLayoutY(pane.getHeight() - flower.getView().getFitHeight());

        flower.display();
    }

    private void addGrass(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Grass grass = new Grass(plant, pane);

        grass.getView().setLayoutX(random.nextDouble() * (pane.getWidth() - grass.getView().getFitWidth()));
        grass.getView().setLayoutY(pane.getHeight() - grass.getView().getFitHeight());

        grass.display();
    }

    public static void main(String[] args) {
        launch(args);
    }

}