package com.structurax.design.ai.engines;

import com.structurax.design.model.GeneratedLayout;
import org.springframework.stereotype.Component;

@Component
public class SunlightScoringEngine {

    public double calculate(GeneratedLayout layout) {

        if (layout.getWindows() == null) {
            return 0;
        }

        int windowCount = layout.getWindows().size();

        return Math.min(windowCount * 10, 100);

    }
}