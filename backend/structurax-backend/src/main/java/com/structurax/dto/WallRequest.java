package com.structurax.dto;

public class WallRequest {

    private Long designProjectId;

    private Integer startX;
    private Integer startY;

    private Integer endX;
    private Integer endY;

    public WallRequest() {
    }

    public Long getDesignProjectId() {
        return designProjectId;
    }

    public void setDesignProjectId(Long designProjectId) {
        this.designProjectId = designProjectId;
    }

    public Integer getStartX() {
        return startX;
    }

    public void setStartX(Integer startX) {
        this.startX = startX;
    }

    public Integer getStartY() {
        return startY;
    }

    public void setStartY(Integer startY) {
        this.startY = startY;
    }

    public Integer getEndX() {
        return endX;
    }

    public void setEndX(Integer endX) {
        this.endX = endX;
    }

    public Integer getEndY() {
        return endY;
    }

    public void setEndY(Integer endY) {
        this.endY = endY;
    }
}