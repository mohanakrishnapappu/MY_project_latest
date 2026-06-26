package com.structurax.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MaterialRequest {

    @NotBlank
    private String materialName;

    @NotBlank
    private String category;

    @NotNull
    private Double quantity;

    @NotBlank
    private String unit;

    @NotNull
    private Double unitPrice;


public String getMaterialName() {
    return materialName;
}

public void setMaterialName(String materialName) {
    this.materialName = materialName;
}

public String getCategory() {
    return category;
}

public void setCategory(String category) {
    this.category = category;
}

public Double getQuantity() {
    return quantity;
}

public void setQuantity(Double quantity) {
    this.quantity = quantity;
}

public String getUnit() {
    return unit;
}

public void setUnit(String unit) {
    this.unit = unit;
}

public Double getUnitPrice() {
    return unitPrice;
}

public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
}
    // Generate Getters and Setters
}