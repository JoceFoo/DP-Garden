package com.dp.group9;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Group 9
 * Virtual Space: Garden
 */
public class App extends Application {
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

        //garden facilities
        // Slide slide = new Slide(root, 80, 400);
        // Seesaw seesaw = new Seesaw(root, 450, 250);

        //hillside facilities
        Swing swing = new Swing(root, 400, 320);
        RopeNetClimbing ropeNetClimbing = new RopeNetClimbing(root, 80, 500);

        //off the city facilities
        // ClimbingWalls climbingWalls = new ClimbingWalls(root, 300, 450);
        // JungleGym jungleGym = new JungleGym(root, 60, 370);

        //mountain facilities
        // MonkeyBars monkeyBars = new MonkeyBars(root, 70, 450);
        // RopeBridge ropeBridge = new RopeBridge(root, 350, 300);

        // Playground hillside = new Playground(root);
        // Swing swing = new Swing(root, 280, 290);
        // hillside.addFacilities(swing);
        // RopeNetClimbing ropeNetClimbing = new RopeNetClimbing(root, 420, 520);
        // hillside.addFacilities(ropeNetClimbing);

        //(playground for off the city)
        //Playground playground = new Playground(Arrays.asList(climbingWalls, jungleGym));

        //(playground for mountain)
        // Playground playground = new Playground(Arrays.asList(monkeyBars, ropeBridge));

        //(playground for garden)
        //Playground playground = new Playground(Arrays.asList(slide, seesaw));

        //(playground for hillside)
        Playground playground = new Playground(Arrays.asList(swing, ropeNetClimbing));
        playground.display();

        // add Buttons and get+show scene
        root.getChildren().addAll(layoutButton, treeButton, flowerButton, grassButton);
        
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