package com.structurax.design.ai.evolution;

import com.structurax.design.model.LayoutCandidate;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LayoutMutationEngine {

    private final Random random = new Random();

    public LayoutCandidate mutate(LayoutCandidate candidate) {

        if (candidate == null) {
            return null;
        }

        LayoutCandidate mutated = new LayoutCandidate();

        mutated.setCandidateNumber(candidate.getCandidateNumber());

        mutated.setRooms(candidate.getRooms());
        mutated.setWalls(candidate.getWalls());
        mutated.setDoors(candidate.getDoors());
        mutated.setWindows(candidate.getWindows());
        mutated.setLayoutScore(candidate.getLayoutScore());

        mutateRoomPositions(mutated);

        return mutated;
    }

    private void mutateRoomPositions(LayoutCandidate candidate) {

        if (candidate.getRooms() == null || candidate.getRooms().isEmpty()) {
            return;
        }

        Room room =
                candidate.getRooms().get(random.nextInt(candidate.getRooms().size()));

        if (room.getPositionX() != null) {
            room.setPositionX(
                    room.getPositionX() + random.nextInt(11) - 5
            );
        }

        if (room.getPositionY() != null) {
            room.setPositionY(
                    room.getPositionY() + random.nextInt(11) - 5
            );
        }

    }
}