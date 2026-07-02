package com.structurax.design.candidate;

import com.structurax.design.ai.constraint.AIConstraintEngine;
import com.structurax.entity.Room;
import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LayoutMutationEngine {

    private final AIConstraintEngine constraintEngine;

    private final Random random = new Random();

    public LayoutMutationEngine(AIConstraintEngine constraintEngine) {

        this.constraintEngine = constraintEngine;

    }

    public void mutate(LayoutCandidate candidate) {

        if (candidate.getRooms() == null) {
            return;
        }

        for (Room room : candidate.getRooms()) {

            improveSunlight(room);

            improvePrivacy(room);

            improveVentilation(room);

            reduceOverlap(room);

        }

    }

    /*
     -------------------------------
     Sunlight Optimization
     -------------------------------
     */

    private void improveSunlight(Room room) {

        if (!constraintEngine.roomRequiresSunlight(room.getRoomType())) {
            return;
        }

        room.setPositionX(
                room.getPositionX() + random.nextInt(4));

    }

    /*
     -------------------------------
     Ventilation Optimization
     -------------------------------
     */

    private void improveVentilation(Room room) {

        if (!constraintEngine.roomRequiresVentilation(room.getRoomType())) {
            return;
        }

        room.setPositionY(
                room.getPositionY() + random.nextInt(4));

    }

    /*
     -------------------------------
     Privacy Optimization
     -------------------------------
     */

    private void improvePrivacy(Room room) {

        if (!constraintEngine.roomRequiresPrivacy(room.getRoomType())) {
            return;
        }

        room.setPositionX(
                room.getPositionX() + 2);

    }

    /*
     -------------------------------
     Reduce overlap
     -------------------------------
     */

    private void reduceOverlap(Room room) {

        room.setPositionX(

                Math.max(0, room.getPositionX())

        );

        room.setPositionY(

                Math.max(0, room.getPositionY())

        );

    }

}