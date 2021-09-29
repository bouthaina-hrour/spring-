package com.emse.spring.faircorp.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Room {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int floor;
    @Column(nullable = false)
    private String name;

    private Double currentTemperature;
    private Double targetTemperature;
   @OneToMany(mappedBy = "room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy = "room")
   private Set<Window> windows;

    public Room(){}

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public Set<Heater> getHeaters() {
        return heaters;
    }

    public Set<Window> getWindows() {
        return windows;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Long getId() {
        return id;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room(String name, int floor){
        this.name=name;
        this.floor=floor;
    }

}
