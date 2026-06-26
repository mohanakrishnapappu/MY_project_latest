package com.structurax.dto;

public class WindowRequest {

    private Long designProjectId;

    private String windowType;

    private Integer positionX;
    private Integer positionY;

    private Double width;

    public WindowRequest() {
    }

    public Long getDesignProjectId() {
        return designProjectId;
    }

    public void setDesignProjectId(Long designProjectId) {
        this.designProjectId = designProjectId;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
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