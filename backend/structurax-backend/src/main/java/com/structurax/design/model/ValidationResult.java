package com.structurax.design.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private boolean valid = true;

    private List<String> errors = new ArrayList<>();

    private List<String> warnings = new ArrayList<>();
    

    public ValidationResult() {
    }

    public boolean isValid() {
        return valid;
    }

    public void invalidate() {
        this.valid = false;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addError(String message) {
        this.valid = false;
        this.errors.add(message);
    }

    public void addWarning(String message) {
        this.warnings.add(message);
    }

    public void merge(ValidationResult other) {

    this.errors.addAll(other.getErrors());

    this.warnings.addAll(other.getWarnings());

    if (!other.isValid()) {
        this.valid = false;
    }
}   
}