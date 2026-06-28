package com.structurax.design.ai.scoring;

import com.structurax.entity.Door;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CirculationScoreEngine {

    public double calculate(List<Door> doors){

        if(doors.isEmpty())
            return 0;

        return Math.min(100, doors.size()*20);

    }

}