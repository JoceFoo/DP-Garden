package com.dp.group9;

public enum LayoutType {
  Garden("Garden", "layout1"),
  MountainView("Mountain view", "layout2"),
  OffTheCity("Off the city", "layout3"),
  Hillside("Hillside", "layout4");

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