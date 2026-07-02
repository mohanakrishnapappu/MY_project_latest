package com.structurax.design.ai.engines;

import com.structurax.design.model.GeneratedLayout;
import org.springframework.stereotype.Component;

@Component
public class VentilationScoringEngine {

    public double calculate(GeneratedLayout layout) {

        if (layout.getWindows() == null) {
            return 0;
        }

        return Math.min(layout.getWindows().size() * 8, 100);

    }

}