package com.dp.group9;

public enum LayoutType {
  GARDEN("Garden", "layout1"),
  MOUNTAIN_VIEW("Mountain view", "layout2"),
  OFF_THE_CITY("Off the city", "layout3"),
  HILLSIDE("Hillside", "layout4");

  private String layoutName;
  private String imageName;

  LayoutType(String layoutName, String imageName) {
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