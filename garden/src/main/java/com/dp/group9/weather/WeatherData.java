package com.dp.group9.weather;

import java.util.ArrayList;

public class WeatherData implements Subject {
    private ArrayList<Observer> observers;
    // private float temperature;
    // private float humidity;
    // private float pressure;
    private String weather;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    public void setWeather(String newWeather) {
        this.weather = newWeather;
        notifyObservers();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(weather);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public String getWeather() {
        return weather;
    }
}
