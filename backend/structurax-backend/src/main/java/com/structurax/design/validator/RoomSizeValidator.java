package com.structurax.design.validator;

import com.structurax.design.model.ValidationResult;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomSizeValidator {

    public ValidationResult validate(List<Room> rooms) {

        ValidationResult result = new ValidationResult();

        for (Room room : rooms) {

            switch (room.getRoomType().toUpperCase()) {

                case "BEDROOM":

                    if (room.getLength() < 10 || room.getWidth() < 10) {

                        result.addWarning(
                                "Bedroom should be at least 10 x 10 ft."
                        );

                    }

                    break;

                case "KITCHEN":

                    if (room.getLength() < 8 || room.getWidth() < 8) {

                        result.addWarning(
                                "Kitchen should be at least 8 x 8 ft."
                        );

                    }

                    break;

                case "BATHROOM":

                    if (room.getLength() < 5 || room.getWidth() < 4) {

                        result.addWarning(
                                "Bathroom should be at least 5 x 4 ft."
                        );

                    }

                    break;

                case "HALL":

                    if (room.getLength() < 15 || room.getWidth() < 12) {

                        result.addWarning(
                                "Hall is smaller than recommended size."
                        );

                    }

                    break;

                default:

                    break;

            }

        }

        return result;

    }

}