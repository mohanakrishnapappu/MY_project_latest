package com.structurax.design.ai.scoring;

import com.structurax.design.ai.analysis.AreaUtilizationEngine;
import com.structurax.design.ai.analysis.CirculationAnalysisEngine;
import com.structurax.design.ai.engines.CirculationScoringEngine;
import com.structurax.design.ai.engines.PrivacyScoringEngine;
import com.structurax.design.ai.engines.SunlightScoringEngine;
import com.structurax.design.ai.engines.VentilationScoringEngine;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Component;

@Component
public class LayoutScoringEngine {

    private final AreaUtilizationEngine areaEngine;
    private final SunlightScoringEngine sunlightEngine;
    private final VentilationScoringEngine ventilationEngine;
    private final PrivacyScoringEngine privacyEngine;
    private final CirculationScoringEngine circulationEngine;
    private final CirculationAnalysisEngine circulationAnalysisEngine;

    public LayoutScoringEngine(
            AreaUtilizationEngine areaEngine,
            SunlightScoringEngine sunlightEngine,
            VentilationScoringEngine ventilationEngine,
            PrivacyScoringEngine privacyEngine,
            CirculationScoringEngine circulationEngine,
            CirculationAnalysisEngine circulationAnalysisEngine) {

        this.areaEngine = areaEngine;
        this.sunlightEngine = sunlightEngine;
        this.ventilationEngine = ventilationEngine;
        this.privacyEngine = privacyEngine;
        this.circulationEngine = circulationEngine;
        this.circulationAnalysisEngine = circulationAnalysisEngine;
    }

    /*
     * -------------------------------------------------------
     * Score a Generated Layout
     * -------------------------------------------------------
     */
    public LayoutScore evaluate(GeneratedLayout layout) {

        LayoutScore score = new LayoutScore();

        score.setSunlightScore(
                sunlightEngine.calculate(layout));

        score.setVentilationScore(
                ventilationEngine.calculate(layout));

        score.setPrivacyScore(
                privacyEngine.calculate(layout));

        /*
         * REAL circulation AI (replaces placeholder engine)
         */
        score.setCirculationScore(
                circulationAnalysisEngine
                        .calculateCirculationScore(
                                layout.getRooms()
                        )
        );

        /*
         * Placeholder for now.
         * Will be replaced with real AreaUtilizationEngine
         * once ProjectRequestDTO is integrated.
         */
        score.setAreaUtilizationScore(
                calculateArea(layout));

        score.setFinalScore(
                calculateFinalScore(score));

        return score;
    }

    /*
     * -------------------------------------------------------
     * Score a Layout Candidate
     * -------------------------------------------------------
     */
    public LayoutScore evaluate(LayoutCandidate candidate) {

        LayoutScore score = new LayoutScore();

        score.setSunlightScore(
                candidate.getWindows() == null
                        ? 0
                        : Math.min(candidate.getWindows().size() * 10, 100));

        score.setVentilationScore(
                candidate.getWindows() == null
                        ? 0
                        : Math.min(candidate.getWindows().size() * 8, 100));

        score.setPrivacyScore(80);

        score.setCirculationScore(85);

        score.setAreaUtilizationScore(90);

        score.setFinalScore(
                calculateFinalScore(score));

        return score;
    }

    /*
     * -------------------------------------------------------
     * Placeholder
     * -------------------------------------------------------
     */
    private double calculateArea(GeneratedLayout layout) {
        return 90;
    }

    /*
     * -------------------------------------------------------
     * Final Weighted Score
     * -------------------------------------------------------
     */
    private double calculateFinalScore(LayoutScore score) {

        return (score.getSunlightScore() * 0.25)
                + (score.getVentilationScore() * 0.20)
                + (score.getPrivacyScore() * 0.20)
                + (score.getCirculationScore() * 0.20)
                + (score.getAreaUtilizationScore() * 0.15);
    }
}