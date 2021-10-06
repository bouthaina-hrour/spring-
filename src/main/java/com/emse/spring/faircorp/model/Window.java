package com.emse.spring.faircorp.model;


import javax.persistence.*;

@Entity
@Table(name = "RWINDOW")
public class Window {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WindowStatus windowStatus;
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Room room;

    public Window() {
    }

    public Room getRoom() {
        return room;
    }

    public Window(String name, WindowStatus status, Room room) {
        this.windowStatus = status;
        this.name = name;
        this.room=room;

    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }
}
