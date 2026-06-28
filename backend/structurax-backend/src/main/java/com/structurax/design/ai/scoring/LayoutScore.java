package com.structurax.design.ai.scoring;

public class LayoutScore {

    private double sunlightScore;
    private double ventilationScore;
    private double privacyScore;
    private double circulationScore;
    private double areaUtilizationScore;
    private double finalScore;

    public double getSunlightScore() {
        return sunlightScore;
    }

    public void setSunlightScore(double sunlightScore) {
        this.sunlightScore = sunlightScore;
    }

    public double getVentilationScore() {
        return ventilationScore;
    }

    public void setVentilationScore(double ventilationScore) {
        this.ventilationScore = ventilationScore;
    }

    public double getPrivacyScore() {
        return privacyScore;
    }

    public void setPrivacyScore(double privacyScore) {
        this.privacyScore = privacyScore;
    }

    public double getCirculationScore() {
        return circulationScore;
    }

    public void setCirculationScore(double circulationScore) {
        this.circulationScore = circulationScore;
    }

    public double getAreaUtilizationScore() {
        return areaUtilizationScore;
    }

    public void setAreaUtilizationScore(double areaUtilizationScore) {
        this.areaUtilizationScore = areaUtilizationScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }
}