package com.structurax.design.ai.scoring;

import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VentilationScoreEngine {

    public double calculate(List<Window> windows){

        if(windows.isEmpty())
            return 0;

        return Math.min(100, windows.size()*20);

    }

}