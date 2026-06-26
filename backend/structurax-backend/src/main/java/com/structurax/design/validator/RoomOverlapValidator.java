package com.structurax.design.validator;

import com.structurax.design.model.ValidationResult;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomOverlapValidator {

    public ValidationResult validate(List<Room> rooms) {

        ValidationResult result = new ValidationResult();

        for (int i = 0; i < rooms.size(); i++) {

            Room r1 = rooms.get(i);

            for (int j = i + 1; j < rooms.size(); j++) {

                Room r2 = rooms.get(j);

                boolean overlap =
                        r1.getPositionX() < r2.getPositionX() + r2.getWidth()
                                &&
                                r1.getPositionX() + r1.getWidth() > r2.getPositionX()
                                &&
                                r1.getPositionY() < r2.getPositionY() + r2.getLength()
                                &&
                                r1.getPositionY() + r1.getLength() > r2.getPositionY();

                if (overlap) {

                    result.addError(

                            r1.getRoomType()

                                    + " overlaps "

                                    + r2.getRoomType()

                    );

                }

            }

        }

        return result;

    }

}