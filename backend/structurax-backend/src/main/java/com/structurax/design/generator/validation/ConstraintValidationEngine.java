package com.structurax.design.generator.validation;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConstraintValidationEngine {

    public boolean validate(List<Room> rooms) {

        if (rooms == null || rooms.isEmpty()) {
            return false;
        }

        for (Room room : rooms) {

            if (room.getLength() == null || room.getLength() <= 0) {
                return false;
            }

            if (room.getWidth() == null || room.getWidth() <= 0) {
                return false;
            }

            if (room.getPositionX() == null || room.getPositionY() == null) {
                return false;
            }
        }

        return true;
    }
}