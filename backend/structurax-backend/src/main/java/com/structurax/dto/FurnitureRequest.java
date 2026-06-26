package com.structurax.dto;

public class FurnitureRequest {

    private Long designProjectId;

    private String furnitureType;

    private Integer positionX;

    private Integer positionY;

    private Double width;

    private Double length;

    public Long getDesignProjectId() {
        return designProjectId;
    }

    public void setDesignProjectId(Long designProjectId) {
        this.designProjectId = designProjectId;
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
}