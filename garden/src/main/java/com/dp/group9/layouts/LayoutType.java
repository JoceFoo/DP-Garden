package com.dp.group9.layouts;

public enum LayoutType {
  GARDEN("Garden", "layout1.jpg"),
  MOUNTAIN_VIEW("Mountain view", "layout2.jpg"),
  OFF_THE_CITY("Off the city", "layout3.jpg"),
  HILLSIDE("Hillside", "layout4.jpg");

  private String layoutName;
  private String imageName;

  private LayoutType(String layoutName, String imageName) {
    this.layoutName = layoutName;
    this.imageName = imageName;
  }
  
  public String getLayoutName() {
    return layoutName;
  }

  public String getImageName() {
    return imageName;
  }
}