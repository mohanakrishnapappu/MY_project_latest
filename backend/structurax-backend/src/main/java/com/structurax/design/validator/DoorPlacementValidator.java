package com.structurax.design.validator;

import com.structurax.design.model.ValidationResult;
import com.structurax.entity.Door;
import com.structurax.entity.Wall;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoorPlacementValidator {

    public ValidationResult validate(List<Door> doors,
                                     List<Wall> walls) {

        ValidationResult result = new ValidationResult();

        for (Door door : doors) {

            boolean onWall = false;

            for (Wall wall : walls) {

                // Horizontal Wall

                if (wall.getStartY().equals(wall.getEndY())) {

                    int wallY = wall.getStartY();

                    int minX = Math.min(wall.getStartX(), wall.getEndX());

                    int maxX = Math.max(wall.getStartX(), wall.getEndX());

                    if (door.getPositionY() == wallY &&
                            door.getPositionX() >= minX &&
                            door.getPositionX() <= maxX) {

                        onWall = true;
                        break;

                    }

                }

                // Vertical Wall

                if (wall.getStartX().equals(wall.getEndX())) {

                    int wallX = wall.getStartX();

                    int minY = Math.min(wall.getStartY(), wall.getEndY());

                    int maxY = Math.max(wall.getStartY(), wall.getEndY());

                    if (door.getPositionX() == wallX &&
                            door.getPositionY() >= minY &&
                            door.getPositionY() <= maxY) {

                        onWall = true;
                        break;

                    }

                }

            }

            if (!onWall) {

                result.addError(
                        "Door at (" +
                                door.getPositionX() +
                                "," +
                                door.getPositionY() +
                                ") is not attached to any wall."
                );

            }

        }

        return result;

    }

}