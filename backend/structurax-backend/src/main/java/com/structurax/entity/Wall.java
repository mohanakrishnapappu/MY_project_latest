package com.structurax.entity;

import jakarta.persistence.*;

@Entity
public class Wall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer startX;
    private Integer startY;

    private Integer endX;
    private Integer endY;

    @ManyToOne
    @JoinColumn(name = "design_project_id")
    private DesignProject designProject;

    public Wall() {
    }

    public Long getId() {
        return id;
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

    public DesignProject getDesignProject() {
        return designProject;
    }

    public void setDesignProject(DesignProject designProject) {
        this.designProject = designProject;
    }
}