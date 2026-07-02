package com.structurax.design.ai.validator;

import com.structurax.design.ai.constraint.AIConstraintEngine;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class LayoutConstraintValidator {

    private final AIConstraintEngine constraintEngine;

    public LayoutConstraintValidator(
            AIConstraintEngine constraintEngine) {

        this.constraintEngine = constraintEngine;
    }

    public boolean validate(GeneratedLayout layout) {

        if (layout == null)
            return false;

        if (layout.getRooms() == null)
            return false;

        for (Room room : layout.getRooms()) {

            if (!validateRoom(room)) {
                return false;
            }

        }

        return true;
    }

    private boolean validateRoom(Room room) {

        if (room.getLength() == null || room.getWidth() == null)
            return false;

        if (room.getLength() <= 0)
            return false;

        if (room.getWidth() <= 0)
            return false;

        if (constraintEngine.getRule(room.getRoomType()) == null)
            return false;

        return true;
    }

}