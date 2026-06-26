package com.structurax.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "windows")
public class Window {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String windowType;

private Integer positionX;
private Integer positionY;

private Double width;

@ManyToOne
@JoinColumn(name = "design_project_id")
private DesignProject designProject;

// getters and setters

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
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

public DesignProject getDesignProject() {
return designProject;
}

public void setDesignProject(DesignProject designProject) {
this.designProject = designProject;
}
}