package com.structurax.design.generator.corridor;

import com.structurax.design.ai.circulation.CirculationPath;
import com.structurax.entity.Corridor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CorridorGenerationEngine {

    public List<Corridor> generateCorridors(
            List<CirculationPath> paths) {

        List<Corridor> corridors = new ArrayList<>();

        for (CirculationPath path : paths) {

            Corridor corridor = new Corridor();

            corridor.setStartX(
                    path.getFrom().getPositionX());

            corridor.setStartY(
                    path.getFrom().getPositionY());

            corridor.setEndX(
                    path.getTo().getPositionX());

            corridor.setEndY(
                    path.getTo().getPositionY());

            corridor.setWidth(4.0);

            corridors.add(corridor);
        }

        return corridors;
    }

}