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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
import javafx.scene.image.Image;
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
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
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

        GardenLayout gardenLayout = GardenLayout.getInstance();
        gardenLayout.setLayout(new LayoutType("Fourth"), root);

        Image backgroundImage = gardenLayout.getLayoutType().getBackgroundImage().getImage();
        // stage.setWidth(backgroundImage.getWidth());
        // stage.setHeight(backgroundImage.getHeight());

        stage.show();
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