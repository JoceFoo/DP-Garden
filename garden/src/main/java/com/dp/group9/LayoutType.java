package com.dp.group9;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class LayoutType {
  private String layoutType;
  private BackgroundImage background;

  public static final String[] LAYOUT_TYPES = { "First", "Second", "Third", "Fourth" };

  public LayoutType(String layoutType) {
    for (String type : LAYOUT_TYPES) {
      if (type.equals(layoutType)) {
        this.layoutType = layoutType;
        try {
          Image image = new Image(ClassLoader.getSystemResourceAsStream(layoutType + ".jpg"));
          BackgroundSize backgroundSize = new BackgroundSize(image.getWidth(), image.getHeight(), true, true, true, true);
          this.background = new BackgroundImage(image,
              BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
              backgroundSize);

        } catch (Exception e) {
          System.out.println("Error loading image: " + e.getMessage());
        }
      }
    }
  }

  public String getLayoutType() {
    return layoutType;
  }

  public BackgroundImage getBackgroundImage() {
    return background;
  }
}
