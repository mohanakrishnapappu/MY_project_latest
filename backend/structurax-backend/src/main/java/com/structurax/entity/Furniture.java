package com.structurax.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "furnitures")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String furnitureType;

    private Integer positionX;

    private Integer positionY;

    private Double width;

    private Double length;

    @ManyToOne
    @JoinColumn(name = "design_project_id")
    private DesignProject designProject;

    public Furniture() {
    }

    public Long getId() {
        return id;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public DesignProject getDesignProject() {
        return designProject;
    }

    public void setDesignProject(DesignProject designProject) {
        this.designProject = designProject;
    }
}