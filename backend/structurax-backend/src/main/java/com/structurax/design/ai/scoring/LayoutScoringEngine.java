package com.structurax.design.ai.scoring;

import com.structurax.design.ai.analysis.AreaUtilizationEngine;
import com.structurax.design.ai.analysis.CirculationAnalysisEngine;
import com.structurax.design.ai.analysis.PrivacyAnalysisEngine;
import com.structurax.design.ai.analysis.SunlightAnalysisEngine;
import com.structurax.design.ai.analysis.VentilationAnalysisEngine;
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

    /*
     * REAL AI Analysis Engines
     */
    private final SunlightAnalysisEngine sunlightAnalysisEngine;
    private final VentilationAnalysisEngine ventilationAnalysisEngine;
    private final PrivacyAnalysisEngine privacyAnalysisEngine;

    /*
     * Existing placeholder scoring engines
     * (Will be removed gradually as AI analysis engines replace them)
     */
    private final SunlightScoringEngine sunlightEngine;
    private final VentilationScoringEngine ventilationEngine;
    private final PrivacyScoringEngine privacyEngine;
    private final CirculationScoringEngine circulationEngine;

    /*
     * Real circulation analysis
     */
    private final CirculationAnalysisEngine circulationAnalysisEngine;

    public LayoutScoringEngine(
            AreaUtilizationEngine areaEngine,
            SunlightAnalysisEngine sunlightAnalysisEngine,
            VentilationAnalysisEngine ventilationAnalysisEngine,
            PrivacyAnalysisEngine privacyAnalysisEngine,
            SunlightScoringEngine sunlightEngine,
            VentilationScoringEngine ventilationEngine,
            PrivacyScoringEngine privacyEngine,
            CirculationScoringEngine circulationEngine,
            CirculationAnalysisEngine circulationAnalysisEngine) {

        this.areaEngine = areaEngine;
        this.sunlightAnalysisEngine = sunlightAnalysisEngine;
        this.ventilationAnalysisEngine = ventilationAnalysisEngine;
        this.privacyAnalysisEngine = privacyAnalysisEngine;

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

        /*
         * REAL AI Sunlight Analysis
         */
        score.setSunlightScore(
                sunlightAnalysisEngine.calculateSunlightScore(
                        layout.getRooms(),
                        layout.getWindows()
                ));

        /*
         * REAL AI Ventilation Analysis
         */
        score.setVentilationScore(
                ventilationAnalysisEngine.calculateVentilationScore(
                        layout.getRooms(),
                        layout.getWindows()
                ));

        /*
         * REAL AI Privacy Analysis
         */
        score.setPrivacyScore(
                privacyAnalysisEngine.calculatePrivacyScore(
                        layout.getRooms()
                ));

        /*
         * REAL AI Circulation Analysis
         */
        score.setCirculationScore(
                circulationAnalysisEngine.calculateCirculationScore(
                        layout.getRooms()
                ));

        /*
         * Placeholder area score.
         * Will use AreaUtilizationEngine after the scoring API
         * is refactored to include ProjectRequestDTO.
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

        /*
         * Candidate scoring still uses placeholders.
         * This will be migrated to the AI analysis engines
         * in a future refactoring.
         */
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