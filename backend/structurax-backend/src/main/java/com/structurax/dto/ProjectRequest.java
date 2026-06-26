package com.structurax.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class ProjectRequest {

    @NotBlank(message = "Project name is required")
private String projectName;

@NotBlank(message = "Location is required")
private String location;

@NotNull(message = "Plot area is required")
@Min(value = 1, message = "Plot area must be greater than 0")
private Double plotArea;

@NotNull(message = "Floors are required")
@Min(value = 1, message = "Floors must be at least 1")
private Integer floors;

@NotBlank(message = "Status is required")
private String status;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(Double plotArea) {
        this.plotArea = plotArea;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}