package com.structurax.design.ai;

public class DesignSuggestion {

    private String title;
    private String description;
    private String type;
    private String priority;

    public DesignSuggestion() {
    }

    public DesignSuggestion(String title, String description, String type, String priority) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = priority;
    }

    // OPTIONAL: constructor for 3 fields (FIX for your error)
    public DesignSuggestion(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = "MEDIUM"; // default enterprise rule
    }

    // GETTERS AND SETTERS

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}