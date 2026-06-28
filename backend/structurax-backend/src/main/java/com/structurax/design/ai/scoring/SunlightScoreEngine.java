package com.structurax.design.ai.scoring;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SunlightScoreEngine {

    public double calculate(List<Room> rooms){

        double score = 0;

        for(Room room : rooms){

            if(room.getPositionY() < 100){

                score += 20;

            }else{

                score += 10;

            }

        }

        return Math.min(score,100);

    }

}