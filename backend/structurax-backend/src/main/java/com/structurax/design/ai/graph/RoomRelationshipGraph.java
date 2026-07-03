package com.structurax.design.ai.graph;

import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoomRelationshipGraph {

    public Map<Room, List<Room>> buildGraph(List<Room> rooms) {

        Map<Room, List<Room>> graph = new HashMap<>();

        for (Room room : rooms) {
            graph.put(room, new ArrayList<>());
        }

        connect(graph, rooms, "HALL", "BEDROOM");
        connect(graph, rooms, "HALL", "KITCHEN");
        connect(graph, rooms, "BEDROOM", "BATHROOM");

        return graph;
    }

    private void connect(

            Map<Room, List<Room>> graph,

            List<Room> rooms,

            String roomA,

            String roomB) {

        Room first = null;
        Room second = null;

        for (Room room : rooms) {

            if (room.getRoomType().equalsIgnoreCase(roomA)
                    && first == null) {

                first = room;
            }

            if (room.getRoomType().equalsIgnoreCase(roomB)
                    && second == null) {

                second = room;
            }

        }

        if (first != null && second != null) {

            graph.get(first).add(second);

            graph.get(second).add(first);

        }

    }

}