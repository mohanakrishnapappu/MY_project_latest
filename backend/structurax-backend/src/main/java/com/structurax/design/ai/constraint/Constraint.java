package com.structurax.design.ai.constraint;

public class Constraint {

    private ConstraintType type;

    private String targetRoom;

    private Object value;

    public Constraint() {
    }

    public Constraint(
            ConstraintType type,
            String targetRoom,
            Object value) {

        this.type = type;
        this.targetRoom = targetRoom;
        this.value = value;

    }

    public ConstraintType getType() {
        return type;
    }

    public void setType(ConstraintType type) {
        this.type = type;
    }

    public String getTargetRoom() {
        return targetRoom;
    }

    public void setTargetRoom(String targetRoom) {
        this.targetRoom = targetRoom;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}