package com.structurax.design.candidate;

import com.structurax.design.model.LayoutCandidate;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LayoutMutationEngine {

    private final Random random = new Random();

    public void mutate(LayoutCandidate candidate) {

        for (Room room : candidate.getRooms()) {

            int dx = random.nextInt(31) - 15;

            int dy = random.nextInt(31) - 15;

            room.setPositionX(room.getPositionX() + dx);

            room.setPositionY(room.getPositionY() + dy);
        }

    }

}