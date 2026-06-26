package com.structurax.entity;

import jakarta.persistence.*;

@Entity
public class Door {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doorType;

    private Integer positionX;
    private Integer positionY;

    private Double width;

    @ManyToOne
    @JoinColumn(name = "design_project_id")
    private DesignProject designProject;

    public Door() {
    }

    public Long getId() {
        return id;
    }

    public String getDoorType() {
        return doorType;
    }

    public void setDoorType(String doorType) {
        this.doorType = doorType;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public DesignProject getDesignProject() {
        return designProject;
    }

    public void setDesignProject(DesignProject designProject) {
        this.designProject = designProject;
    }
}