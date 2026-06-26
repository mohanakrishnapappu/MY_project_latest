package com.structurax.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberRoleUpdateRequest {

    @NotBlank
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}