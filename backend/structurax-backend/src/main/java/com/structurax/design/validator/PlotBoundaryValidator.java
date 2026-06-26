package com.structurax.design.validator;

import com.structurax.design.model.ValidationResult;
import com.structurax.entity.DesignProject;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlotBoundaryValidator {

    public ValidationResult validate(DesignProject project, List<Room> rooms) {

        ValidationResult result = new ValidationResult();

        double plotWidth = Math.sqrt(project.getPlotArea());
        double plotHeight = Math.sqrt(project.getPlotArea());

        for (Room room : rooms) {

            double roomRight =
                    room.getPositionX() + room.getWidth();

            double roomBottom =
                    room.getPositionY() + room.getLength();

            if (room.getPositionX() < 0
                    || room.getPositionY() < 0) {

                result.addError(
                        room.getRoomType()
                                + " starts outside plot."
                );
            }

            if (roomRight > plotWidth) {

                result.addError(
                        room.getRoomType()
                                + " exceeds plot width."
                );
            }

            if (roomBottom > plotHeight) {

                result.addError(
                        room.getRoomType()
                                + " exceeds plot height."
                );
            }

        }

        return result;

    }

}