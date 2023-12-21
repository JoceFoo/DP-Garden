package com.dp.group9;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class LayoutType {
  private String layoutName;
  private BackgroundImage background;

  public static final Map<String, String> LAYOUT_TYPES = new HashMap<String, String>(Map.ofEntries(
    Map.entry("Garden", "layout1"),
    Map.entry("Mountain view", "layout2"),
    Map.entry("Off the city", "layout3"),
    Map.entry("Hillside", "layout4")
  ));

  public LayoutType() { // get first key by default
    String firstKey = LAYOUT_TYPES.keySet().iterator().next(); 
    this.layoutName = firstKey;
    this.background = loadImage(LAYOUT_TYPES.get(firstKey));
  }

  public LayoutType(String layoutType) {
    for (String value : LAYOUT_TYPES.values()) {
      if (value.equals(layoutType)) {
        this.layoutName = layoutType;
        this.background = loadImage(layoutType);
      }
    }
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

  public String getLayoutName() {
    return layoutName;
  }

  public BackgroundImage getBackgroundImage() {
    return background;
  }
}
