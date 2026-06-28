package com.structurax.design.ai.scoring;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrivacyScoreEngine {

    public double calculate(List<Room> rooms){

        double score = 100;

        for(Room room : rooms){

            if(room.getRoomType().equalsIgnoreCase("BEDROOM")){

                if(room.getPositionX() < 80){

                    score -= 10;

                }

            }

        }

        return Math.max(score,0);

    }

}