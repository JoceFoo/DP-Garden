package com.dp.group9;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/**
 * Group 9
 * Virtual Space: Garden
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 750);
        GardenLayout gardenLayout = GardenLayout.getInstance();
        gardenLayout.setLayout(new LayoutType(), root);

        MenuButton layoutButton = new MenuButton(gardenLayout.getLayoutType().getLayoutName());
        layoutButton.setLayoutX(10);
        layoutButton.setLayoutY(10);
        LayoutType.LAYOUT_TYPES.forEach((String key, String value) -> {
            MenuItem menuItem = new MenuItem(key);
            menuItem.setOnAction(e -> {
                gardenLayout.setLayout(new LayoutType(value), root);
                layoutButton.setText(key);
            });
            layoutButton.getItems().add(menuItem);
        });
        root.getChildren().add(layoutButton);

        // Tree
        Button treeButton = new Button("Add Tree");
        treeButton.setLayoutX(layoutButton.getLayoutX() + layoutButton.getWidth() + 120); // beside the layout button
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
        stage.setResizable(false);
        stage.centerOnScreen();
        
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

        flower.getFlowerView().setLayoutX(random.nextDouble() * (pane.getWidth() - flower.getFlowerView().getFitWidth()));
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