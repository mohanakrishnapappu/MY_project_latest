package com.structurax.design.rules;

import com.structurax.design.model.GeneratedLayout;
import org.springframework.stereotype.Component;

@Component
public class MinimumDoorRule implements LayoutRule {

    @Override
    public boolean validate(GeneratedLayout layout) {

        return layout.getDoors() != null
                &&
                !layout.getDoors().isEmpty();

    }

    @Override
    public String getRuleName() {
        return "Minimum Door Rule";
    }

    @Override
    public String getFailureMessage() {
        return "Layout must contain at least one door.";
    }

}