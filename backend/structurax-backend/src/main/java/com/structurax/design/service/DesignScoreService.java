package com.structurax.design.service;

import com.structurax.entity.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignScoreService {

    public int calculateScore(
            DesignProject project,
            List<Room> rooms,
            List<Wall> walls,
            List<Door> doors,
            List<Window> windows,
            List<Furniture> furniture) {

        int score = 100;

        //---------------------------------
        // Minimum rooms
        //---------------------------------

        if (rooms.size() < 3)
            score -= 20;

        //---------------------------------
        // Windows
        //---------------------------------

        if (windows.size() < rooms.size())
            score -= 10;

        //---------------------------------
        // Doors
        //---------------------------------

        if (doors.isEmpty())
            score -= 15;

        //---------------------------------
        // Furniture
        //---------------------------------

        if (furniture.isEmpty())
            score -= 15;

        //---------------------------------
        // Plot Utilization
        //---------------------------------

        double totalRoomArea = 0;

        for (Room room : rooms) {

            totalRoomArea += room.getLength() * room.getWidth();

        }

        double utilization =
                totalRoomArea / project.getPlotArea();

        if (utilization > 0.85)
            score -= 10;

        if (utilization < 0.45)
            score -= 5;

        if (score < 0)
            score = 0;

        return score;

    }

}