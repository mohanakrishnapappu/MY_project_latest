package com.structurax.design.validator;

import com.structurax.design.model.ValidationResult;
import com.structurax.entity.Window;
import com.structurax.entity.Wall;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WindowPlacementValidator {

    public ValidationResult validate(List<Window> windows,
                                     List<Wall> walls) {

        ValidationResult result = new ValidationResult();

        for (Window window : windows) {

            boolean onWall = false;

            for (Wall wall : walls) {

                // Horizontal Wall

                if (wall.getStartY().equals(wall.getEndY())) {

                    int wallY = wall.getStartY();

                    int minX = Math.min(wall.getStartX(), wall.getEndX());

                    int maxX = Math.max(wall.getStartX(), wall.getEndX());

                    if (window.getPositionY() == wallY &&
                            window.getPositionX() >= minX &&
                            window.getPositionX() <= maxX) {

                        onWall = true;
                        break;

                    }

                }

                // Vertical Wall

                if (wall.getStartX().equals(wall.getEndX())) {

                    int wallX = wall.getStartX();

                    int minY = Math.min(wall.getStartY(), wall.getEndY());

                    int maxY = Math.max(wall.getStartY(), wall.getEndY());

                    if (window.getPositionX() == wallX &&
                            window.getPositionY() >= minY &&
                            window.getPositionY() <= maxY) {

                        onWall = true;
                        break;

                    }

                }

            }

            if (!onWall) {

                result.addError(
                        "Window at (" +
                                window.getPositionX() +
                                "," +
                                window.getPositionY() +
                                ") is not attached to any wall."
                );

            }

        }

        return result;

    }

}