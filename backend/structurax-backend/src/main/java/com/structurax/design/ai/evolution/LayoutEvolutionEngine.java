package com.structurax.design.ai.evolution;

import com.structurax.design.ai.scoring.LayoutScoringEngine;
import com.structurax.design.candidate.LayoutMutationEngine;
import com.structurax.design.model.GeneratedLayout;
import com.structurax.design.model.LayoutCandidate;
import com.structurax.design.optimizer.BestLayoutSelector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LayoutEvolutionEngine {

    private final LayoutMutationEngine mutationEngine;
    private final LayoutScoringEngine scoringEngine;
    private final BestLayoutSelector selector;
    private final LayoutFitnessFilter fitnessFilter;

    public LayoutEvolutionEngine(
            LayoutMutationEngine mutationEngine,
            LayoutScoringEngine scoringEngine,
            BestLayoutSelector selector,
            LayoutFitnessFilter fitnessFilter) {

        this.mutationEngine = mutationEngine;
        this.scoringEngine = scoringEngine;
        this.selector = selector;
        this.fitnessFilter = fitnessFilter;
    }

    public LayoutCandidate evolve(List<LayoutCandidate> candidates) {

        List<LayoutCandidate> population = candidates;

        for (int generation = 0; generation < 30; generation++) {

            for (LayoutCandidate candidate : population) {

                mutationEngine.mutate(candidate);

                GeneratedLayout layout = new GeneratedLayout();

                layout.setRooms(candidate.getRooms());
                layout.setWalls(candidate.getWalls());
                layout.setDoors(candidate.getDoors());
                layout.setWindows(candidate.getWindows());

                candidate.setLayoutScore(
                        scoringEngine.evaluate(layout));
            }

            population = fitnessFilter.selectTopCandidates(
                    population,
                    Math.max(5, population.size() / 2));
        }

        return selector.selectBest(population);
    }
}