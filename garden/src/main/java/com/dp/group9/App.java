package com.dp.group9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();
        root.setId("pane");
        
        Scene scene = new Scene(root);
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

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}