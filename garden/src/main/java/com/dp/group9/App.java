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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

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

    private WeatherData weatherData = new WeatherData();
    // Create an instance of WeatherPlant for a specific plant
    WeatherPlant Sunflower = new WeatherPlant("Sunflower"); // Change the name as needed
    private WeatherStation weatherStation;
    Canvas canvas = new Canvas(900, 720);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage stage) throws IOException {

        // weatherData.registerObserver(this);
        weatherData.registerObserver(Sunflower);
        // Canvas canvas = new Canvas(900, 720);
        // GraphicsContext gc = canvas.getGraphicsContext2D();
        weatherStation = new WeatherStation(gc, weatherData);
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, 600, 750); // maintain 4:5 (width to height) ratio
        GardenLayout gardenLayout = GardenLayout.getInstance();
        gardenLayout.setLayout(new LayoutType(), root);

        // Garden Layout
        MenuButton layoutButton = new MenuButton(gardenLayout.getLayoutType().getLayoutName());
        layoutButton.setLayoutX(10);
        layoutButton.setLayoutY(10);
        layoutButton.setPrefWidth(130);
        LayoutType.LAYOUT_TYPES.forEach((String key, String value) -> {
            MenuItem menuItem = new MenuItem(key);
            menuItem.setOnAction(e -> {
                gardenLayout.setLayout(new LayoutType(value), root);
                layoutButton.setText(key);
            });
            layoutButton.getItems().add(menuItem);
        });

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

        // add Buttons and get+show scene
        root.getChildren().addAll(treeButton, flowerButton, grassButton);

        stage.setTitle("Garden");
        stage.setScene(scene);
        stage.setMaxWidth(900);
        stage.setMaxHeight(720);
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.show();
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
                gc.clearRect(0, 0, 900, 720);
                weatherStation.drawWeather();
            }
        };
        animationTimer.start();
    }

    private void showWeatherDialog() {
        ChoiceDialog<String> weatherDialog = new ChoiceDialog<>("Sunny", "Sunny", "Rainy", "Snowy", "Windy", "Stormy");
        weatherDialog.setTitle("Select Weather");
        weatherDialog.setHeaderText(null);
        weatherDialog.setContentText("Choose the current weather:");

        Optional<String> selectedWeather = weatherDialog.showAndWait();
        if (selectedWeather.isPresent()) {
            String weather = selectedWeather.get();
            weatherData.setWeather(weather);
            weatherStation.update(weather);
        }
    }

    public void displayWeatherConfirmation(String weather) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Weather Set");
        alert.setHeaderText(null);
        alert.setContentText("The current weather is set to: " + weather);

        alert.showAndWait();
    }

    private void addTree(Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Tree tree = new Tree(plant, pane);
        double start = pane.getWidth() / 3;
        double end = 2 * start;

        double randomX;
        if (random.nextBoolean()) {
            randomX = random.nextDouble() * (start - tree.getTreeView().getFitWidth()) - start;
        } else {
            randomX = end + random.nextDouble() * (start - tree.getTreeView().getFitWidth() / 2);
        }

        int minY = 150;
        int maxY = 200;
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