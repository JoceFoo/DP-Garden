package com.dp.group9;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class GardenLayout {
  private static GardenLayout instance = new GardenLayout();
  private LayoutType layoutType;
  
  private Background background;

  public static GardenLayout getInstance() {
    return instance;
  }

  public void setLayout(LayoutType layoutType, Pane root) {
    this.layoutType = layoutType;
    this.background = new Background(this.layoutType.getBackgroundImage());
    root.setBackground(this.background);
  }

  public LayoutType getLayoutType() {
    return layoutType;
  }

  public Background getBackground() {
    return background;
  }
}