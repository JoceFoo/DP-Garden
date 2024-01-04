package com.dp.group9;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import com.dp.group9.animals.Animal;
import com.dp.group9.animals.AnimalFactory;
import com.dp.group9.animals.BirdFactory;
import com.dp.group9.animals.ButterflyFactory;
import com.dp.group9.animals.FrogFactory;
import com.dp.group9.animals.SnailFactory;
import com.dp.group9.facilities.ClimbingWalls;
import com.dp.group9.facilities.JungleGym;
import com.dp.group9.facilities.MonkeyBars;
import com.dp.group9.facilities.Playground;
import com.dp.group9.facilities.RopeBridge;
import com.dp.group9.facilities.RopeNetClimbing;
import com.dp.group9.facilities.Seesaw;
import com.dp.group9.facilities.Slide;
import com.dp.group9.facilities.Swing;
import com.dp.group9.layouts.GardenLayout;
import com.dp.group9.layouts.LayoutType;
import com.dp.group9.plants.Flower;
import com.dp.group9.plants.Grass;
import com.dp.group9.plants.Plant;
import com.dp.group9.plants.SimplePlant;
import com.dp.group9.plants.Tree;
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

    private VBox animalChoiceBox;
    private Animal bird;
    private Animal butterfly;
    private Animal snail;
    private Animal frog;

    private WeatherData weatherData = new WeatherData();// Subject
    private WeatherStation weatherStation;// Observer

    WeatherPlant weatherPlant = new WeatherPlant("weatherPlant", weatherData);// Observer
    WeatherAnimal weatherAnimal = new WeatherAnimal("weatherAnimal", weatherData);// Observer
    private Canvas canvas;
    private GraphicsContext gc;

    private String lastSelectedWeather = "No Weather Selected";

    @Override
    public void start(Stage stage) throws IOException {
        canvas = new Canvas(1000, 750);
        gc = canvas.getGraphicsContext2D();

        canvas = new Canvas(1000, 750);
        gc = canvas.getGraphicsContext2D();

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, 1000, 750); // maintain 4:3 (width to height) ratio
        GardenLayout gardenLayout = GardenLayout.getInstance();
        gardenLayout.setLayout(LayoutType.GARDEN.getLayoutName(), root);
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
                // remove all children except canvas and gc
                root.getChildren().removeIf(
                        (node) -> !(node instanceof Button || node instanceof MenuButton || node instanceof Canvas));
                addPlayground(type.getLayoutName(), root);
                root.getChildren().add(animalChoiceBox);
                root.getChildren().addAll(bird.getImageView(), butterfly.getImageView(),
                        snail.getImageView(),
                        frog.getImageView());
                root.getChildren().addAll(weatherPlant.getWeatherPlantView(), weatherAnimal.getWeatherAnimalView());
            });
            layoutButton.getItems().add(menuItem);
        }

        // Create Animal logic
        animalChoiceBox = new VBox();
        animalChoiceBox.setLayoutX(layoutButton.getLayoutX() + layoutButton.getWidth() + 140);
        animalChoiceBox.setLayoutY(0);

        ComboBox<String> animalComboBox = new ComboBox<>(
                FXCollections.observableArrayList("Bird", "Butterfly", "Snail", "Frog"));
        animalComboBox.setValue("Choose Animal");
        animalComboBox.setOnAction(event -> handleAnimalSelection(animalComboBox));

        VBox.setMargin(animalComboBox, new javafx.geometry.Insets(10));
        animalChoiceBox.getChildren().add(animalComboBox);

        root.getChildren().add(animalChoiceBox);

        // Tree
        Button treeButton = new Button("Add Tree");
        treeButton.setLayoutX(animalChoiceBox.getLayoutX() + animalChoiceBox.getWidth() + 150); // beside the animal
                                                                                                // button
        treeButton.setLayoutY(10);

        treeButton.setOnAction(e -> addTree(gardenLayout.getLayoutName(), root));

        // Flower
        Button flowerButton = new Button("Add Flower");
        flowerButton.setLayoutX(treeButton.getLayoutX() + treeButton.getWidth() + 85); // beside the tree button
        flowerButton.setLayoutY(10);

        flowerButton.setOnAction(e -> addFlower(root));

        // Grass
        Button grassButton = new Button("Add Grass");
        grassButton.setLayoutX(flowerButton.getLayoutX() + flowerButton.getWidth() + 95);// beside the flower button
        grassButton.setLayoutY(10);
        grassButton.setOnAction(e -> addGrass(root));

        // Add Bird
        AnimalFactory birdFactory = new BirdFactory();
        bird = birdFactory.createAnimal();

        bird.getImageView().setVisible(false);
        bird.getImageView().setTranslateX(15);
        bird.getImageView().setTranslateY(50);

        bird.display();
        bird.setupMouseHandlers();

        // Add Butterfly
        AnimalFactory butterflyFactory = new ButterflyFactory();
        butterfly = butterflyFactory.createAnimal();

        butterfly.getImageView().setVisible(false);
        butterfly.getImageView().setTranslateX(scene.getWidth() - 250); // 750
        butterfly.getImageView().setTranslateY(scene.getHeight() / 2 - 30); // 320

        butterfly.display();
        butterfly.setupMouseHandlers();

        // Add Snail
        AnimalFactory snailFactory = new SnailFactory();
        snail = snailFactory.createAnimal();

        snail.getImageView().setVisible(false);
        snail.getImageView().setTranslateX(350);
        snail.getImageView().setTranslateY(600);

        snail.display();
        snail.setupMouseHandlers();

        // Add Snail
        AnimalFactory frogFactory = new FrogFactory();
        frog = frogFactory.createAnimal();

        frog.getImageView().setVisible(false);
        frog.getImageView().setTranslateX(scene.getWidth() / 2 - 25);
        frog.getImageView().setTranslateY(scene.getHeight() / 2 + 80);

        frog.display();
        frog.setupMouseHandlers();

        root.getChildren().addAll(bird.getImageView(), butterfly.getImageView(), snail.getImageView(),
                frog.getImageView());

        // Weather
        Button weatherButton = new Button("Choose Weather");
        weatherButton.setLayoutX(grassButton.getLayoutX() + grassButton.getWidth() + 90);// beside the grass button
        weatherButton.setLayoutY(10);
        weatherButton.setOnAction(e -> showWeatherDialog());
        MenuButton observerMenuButton = new MenuButton("Observer Options");
        observerMenuButton.setLayoutX(weatherButton.getLayoutX() + weatherButton.getWidth() + 120);
        observerMenuButton.setLayoutY(10);

        // Modify this section to initialize menu items as selected
        CheckMenuItem weatherPlantItem = createObserverMenuItem("Weather Plant");
        CheckMenuItem weatherAnimalItem = createObserverMenuItem("Weather Animal");
        CheckMenuItem weatherStationItem = createObserverMenuItem("Weather Station");

        observerMenuButton.getItems().addAll(weatherPlantItem, weatherAnimalItem, weatherStationItem);

        // add Buttons and get+show scene
        root.getChildren().addAll(layoutButton, treeButton, flowerButton,
                grassButton, weatherButton, observerMenuButton, weatherPlant.getWeatherPlantView(),
                weatherAnimal.getWeatherAnimalView());

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

    private CheckMenuItem createObserverMenuItem(String text) {
        CheckMenuItem checkMenuItem = new CheckMenuItem(text);
        checkMenuItem.setOnAction(e -> handleObserverCheckbox(checkMenuItem));

        return checkMenuItem;
    }

    private void handleObserverCheckbox(CheckMenuItem checkMenuItem) {
        if (checkMenuItem.getText() == "Weather Plant") {
            if (checkMenuItem.isSelected()) {
                weatherData.registerObserver(weatherPlant);
                // weatherPlant.getWeatherPlantView().setVisible(true);
            } else {
                weatherData.removeObserver(weatherPlant);
                // weatherPlant.getWeatherPlantView().setVisible(false);
            }
        } else if (checkMenuItem.getText() == "Weather Animal") {
            if (checkMenuItem.isSelected()) {
                weatherData.registerObserver(weatherAnimal);
                // weatherAnimal.getWeatherAnimalView().setVisible(true);
            } else {
                weatherData.removeObserver(weatherAnimal);
                // weatherAnimal.getWeatherAnimalView().setVisible(false);
            }
        } else if (checkMenuItem.getText() == "Weather Station") {
            if (checkMenuItem.isSelected()) {
                weatherData.registerObserver(weatherStation);

            } else {
                weatherData.removeObserver(weatherStation);
            }
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
        if (layoutType.equals(LayoutType.GARDEN.getLayoutName())) {
            // garden facilities
            Slide slide = new Slide(root, 80, 350);
            Seesaw seesaw = new Seesaw(root, 550, 270);
            playground.addFacilities(slide, seesaw);
        } else if (layoutType.equals(LayoutType.HILLSIDE.getLayoutName())) {
            // hillside facilities
            Swing swing = new Swing(root, 700, 320);
            RopeNetClimbing ropeNetClimbing = new RopeNetClimbing(root, 80, 500);
            playground.addFacilities(swing, ropeNetClimbing);
        } else if (layoutType.equals(LayoutType.OFF_THE_CITY.getLayoutName())) {
            // off the city facilities
            ClimbingWalls climbingWalls = new ClimbingWalls(root, 540, 480);
            JungleGym jungleGym = new JungleGym(root, 250, 370);
            playground.addFacilities(climbingWalls, jungleGym);
        } else if (layoutType.equals(LayoutType.MOUNTAIN_VIEW.getLayoutName())) {
            // mountain facilities
            MonkeyBars monkeyBars = new MonkeyBars(root, 70, 380);
            RopeBridge ropeBridge = new RopeBridge(root, 680, 300);
            playground.addFacilities(monkeyBars, ropeBridge);
        }
        playground.display();
    }

    private void addTree(String layoutType, Pane pane) {
        Random random = new Random();

        Plant plant = new SimplePlant();
        Tree tree = new Tree(plant, pane);
        double start = pane.getWidth() / 3;
        double end = 2 * start;
        double randomX = 0.00;
        int minY = 0;
        int maxY = 10;

        if (layoutType.equals(LayoutType.GARDEN.getLayoutName())
                || layoutType.equals(LayoutType.MOUNTAIN_VIEW.getLayoutName())) {
            if (random.nextBoolean()) {
                randomX = random.nextDouble() * (start - tree.getView().getFitWidth()) - 250;
            } else {
                randomX = end + random.nextDouble() * (start - tree.getView().getFitWidth() / 2) + 65;
            }

            minY = 110;
            maxY = 130;
        } else if (layoutType.equals(LayoutType.OFF_THE_CITY.getLayoutName())
                || layoutType.equals(LayoutType.HILLSIDE.getLayoutName())) {
            if (random.nextBoolean()) {
                randomX = random.nextDouble() * (start - tree.getView().getFitWidth() - 400);
            } else {
                randomX = end + random.nextDouble() * (start - tree.getView().getFitWidth() / 2);
            }

            minY = 60;
            maxY = 65;
        }

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

    private void handleAnimalSelection(ComboBox<String> animalComboBox) {
        String selectedAnimal = animalComboBox.getValue();
        if (selectedAnimal != null) {
            // Toggle the visibility of the selected animal image
            if ("Bird".equals(selectedAnimal)) {
                bird.getImageView().setVisible(!bird.getImageView().isVisible());
            } else if ("Butterfly".equals(selectedAnimal)) {
                butterfly.getImageView().setVisible(!butterfly.getImageView().isVisible());
            } else if ("Snail".equals(selectedAnimal)) {
                snail.getImageView().setVisible(!snail.getImageView().isVisible());
            } else if ("Frog".equals(selectedAnimal)) {
                frog.getImageView().setVisible(!frog.getImageView().isVisible());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}