package com.structurax.design.ai.scoring;

import com.structurax.entity.Door;
import com.structurax.entity.Room;
import com.structurax.entity.Window;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LayoutScoreEngine {

    private final SunlightScoreEngine sunlight;

    private final VentilationScoreEngine ventilation;

    private final PrivacyScoreEngine privacy;

    private final CirculationScoreEngine circulation;

    private final AreaUtilizationScoreEngine area;

    public LayoutScoreEngine(
            SunlightScoreEngine sunlight,
            VentilationScoreEngine ventilation,
            PrivacyScoreEngine privacy,
            CirculationScoreEngine circulation,
            AreaUtilizationScoreEngine area){

        this.sunlight = sunlight;
        this.ventilation = ventilation;
        this.privacy = privacy;
        this.circulation = circulation;
        this.area = area;

    }

    public LayoutScore evaluate(

            List<Room> rooms,

            List<Door> doors,

            List<Window> windows,

            double efficiency){

        LayoutScore score = new LayoutScore();

        score.setSunlightScore(
                sunlight.calculate(rooms));

        score.setVentilationScore(
                ventilation.calculate(windows));

        score.setPrivacyScore(
                privacy.calculate(rooms));

        score.setCirculationScore(
                circulation.calculate(doors));

        score.setAreaUtilizationScore(
                area.calculate(efficiency));

        double finalScore =

                score.getSunlightScore()*0.20

                        +

                        score.getVentilationScore()*0.20

                        +

                        score.getPrivacyScore()*0.20

                        +

                        score.getCirculationScore()*0.20

                        +

                        score.getAreaUtilizationScore()*0.20;

        score.setFinalScore(finalScore);

        return score;

    }

}