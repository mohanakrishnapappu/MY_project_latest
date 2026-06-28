package com.structurax.design.ai.scoring;

import org.springframework.stereotype.Component;

@Component
public class AreaUtilizationScoreEngine {

    public double calculate(double efficiency){

        return efficiency*100;

    }

}