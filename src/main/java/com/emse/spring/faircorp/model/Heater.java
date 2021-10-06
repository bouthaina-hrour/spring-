package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
public class Heater {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Long power;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus HeaterStatus;

    @ManyToOne(optional = false)
    private Room room;

    public Heater(){}
    public Heater(String name,HeaterStatus heaterStatus,Room room){
        this.HeaterStatus=heaterStatus;
        this.name=name;
        this.room=room;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public com.emse.spring.faircorp.model.HeaterStatus getHeaterStatus() {
        return HeaterStatus;
    }

    public void setHeaterStatus(com.emse.spring.faircorp.model.HeaterStatus heaterStatus) {
        HeaterStatus = heaterStatus;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
