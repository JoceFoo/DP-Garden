package com.dp.group9;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class GardenLayout {
  private static GardenLayout instance = new GardenLayout();
  private String layoutName;
  
  private BackgroundImage backgroundImage;

  public static GardenLayout getInstance() {
    return instance;
  }

  public void setLayout(String layoutType, Pane root) {
    for (LayoutType type : LayoutType.values()) {
      if (type.getLayoutName().equals(layoutType)) {
        this.layoutName = type.getLayoutName();
        this.backgroundImage = loadImage(type.getImageName());
      }
    }
    Background background = new Background(backgroundImage);
    root.setBackground(background);
  }

  public String getLayoutName() {
    return layoutName;
  }

  public BackgroundImage getBackgroundImage() {
    return backgroundImage;
  }

  private BackgroundImage loadImage(String filename) {
    BackgroundImage background = null;
    try {
      Image image = new Image(ClassLoader.getSystemResourceAsStream(filename + ".jpg"));
      BackgroundSize backgroundSize = new BackgroundSize(image.getWidth(), image.getHeight(), true, true, true, true);
      background = new BackgroundImage(image,
          BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
          backgroundSize);
    } catch (Exception e) {
      System.out.println("Error loading image: " + e.getMessage());
      return null;
    }
    return background;
  }
}