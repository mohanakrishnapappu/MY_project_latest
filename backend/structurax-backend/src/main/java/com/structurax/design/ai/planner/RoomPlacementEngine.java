package com.structurax.design.ai.planner;

import com.structurax.design.dto.ProjectRequestDTO;
import com.structurax.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RoomPlacementEngine {

    public void placeRooms(
            ProjectRequestDTO request,
            List<Room> rooms,
            Map<String, String> zones) {

        int publicX = 5;
        int publicY = 5;

        int semiX = 40;
        int semiY = 5;

        int privateX = 5;
        int privateY = 40;

        int serviceX = 40;
        int serviceY = 40;

        for (Room room : rooms) {

            String zone = zones.get(room.getRoomType());

            if (zone == null) {
                continue;
            }

            switch (zone) {

                case "PUBLIC":

                    room.setPositionX(publicX);
                    room.setPositionY(publicY);

                    publicY += room.getLength().intValue() + 3;
                    break;

                case "SEMI_PRIVATE":

                    room.setPositionX(semiX);
                    room.setPositionY(semiY);

                    semiY += room.getLength().intValue() + 3;
                    break;

                case "PRIVATE":

                    room.setPositionX(privateX);
                    room.setPositionY(privateY);

                    privateY += room.getLength().intValue() + 3;
                    break;

                case "SERVICE":

                    room.setPositionX(serviceX);
                    room.setPositionY(serviceY);

                    serviceY += room.getLength().intValue() + 3;
                    break;

                default:
                    break;
            }
        }
    }
}