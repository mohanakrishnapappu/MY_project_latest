package com.structurax.design.validator;

import com.structurax.design.model.ValidationResult;
import com.structurax.entity.Furniture;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FurnitureCollisionValidator {

    public ValidationResult validate(List<Furniture> furnitureList,
                                     List<Room> rooms) {

        ValidationResult result = new ValidationResult();

        // ==========================
        // Furniture inside room check
        // ==========================

        for (Furniture furniture : furnitureList) {

            boolean insideRoom = false;

            for (Room room : rooms) {

                double roomLeft = room.getPositionX();
                double roomTop = room.getPositionY();

                double roomRight =
                        room.getPositionX() + room.getWidth();

                double roomBottom =
                        room.getPositionY() + room.getLength();

                double furnitureRight =
                        furniture.getPositionX() + furniture.getWidth();

                double furnitureBottom =
                        furniture.getPositionY() + furniture.getLength();

                if (furniture.getPositionX() >= roomLeft
                        && furniture.getPositionY() >= roomTop
                        && furnitureRight <= roomRight
                        && furnitureBottom <= roomBottom) {

                    insideRoom = true;
                    break;
                }

            }

            if (!insideRoom) {

                result.addError(

                        furniture.getFurnitureType()

                                + " is outside every room."

                );

            }

        }

        // ==========================
        // Furniture collision
        // ==========================

        for (int i = 0; i < furnitureList.size(); i++) {

            Furniture f1 = furnitureList.get(i);

            for (int j = i + 1; j < furnitureList.size(); j++) {

                Furniture f2 = furnitureList.get(j);

                boolean overlap =

                        f1.getPositionX() < f2.getPositionX() + f2.getWidth()

                                &&

                                f1.getPositionX() + f1.getWidth() > f2.getPositionX()

                                &&

                                f1.getPositionY() < f2.getPositionY() + f2.getLength()

                                &&

                                f1.getPositionY() + f1.getLength() > f2.getPositionY();

                if (overlap) {

                    result.addError(

                            f1.getFurnitureType()

                                    + " overlaps "

                                    + f2.getFurnitureType()

                    );

                }

            }

        }

        return result;

    }

}