package com.structurax.dto;

public class DoorRequest {

    private Long designProjectId;

    private String doorType;

    private Integer positionX;
    private Integer positionY;

    private Double width;

    public DoorRequest() {
    }

    public Long getDesignProjectId() {
        return designProjectId;
    }

    public void setDesignProjectId(Long designProjectId) {
        this.designProjectId = designProjectId;
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
}