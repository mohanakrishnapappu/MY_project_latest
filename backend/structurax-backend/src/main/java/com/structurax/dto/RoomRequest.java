package com.structurax.dto;

public class RoomRequest {

    private Long designProjectId;

    private String roomType;

    private Double length;

    private Double width;

    private Integer positionX;

    private Integer positionY;

    public RoomRequest() {
    }

    public Long getDesignProjectId() {
        return designProjectId;
    }

    public void setDesignProjectId(Long designProjectId) {
        this.designProjectId = designProjectId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
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
}