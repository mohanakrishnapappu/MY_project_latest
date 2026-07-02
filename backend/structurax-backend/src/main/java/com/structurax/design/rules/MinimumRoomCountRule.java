package com.structurax.design.rules;

import com.structurax.design.model.GeneratedLayout;
import org.springframework.stereotype.Component;

@Component
public class MinimumRoomCountRule implements LayoutRule {

    @Override
    public boolean validate(GeneratedLayout layout) {

        return layout.getRooms() != null
                &&
                layout.getRooms().size() >= 2;

    }

    @Override
    public String getRuleName() {
        return "Minimum Room Count";
    }

    @Override
    public String getFailureMessage() {
        return "Layout must contain at least two rooms.";
    }

}