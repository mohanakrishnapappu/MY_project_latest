package com.structurax.design.optimizer;

import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BestLayoutSelector {

    public LayoutCandidate selectBest(List<LayoutCandidate> candidates) {

        if (candidates == null || candidates.isEmpty()) {
            throw new IllegalArgumentException("No layout candidates available.");
        }

        LayoutCandidate best = null;

        for (LayoutCandidate candidate : candidates) {

            if (candidate == null || candidate.getLayoutScore() == null) {
                continue;
            }

            if (best == null
                    || best.getLayoutScore() == null
                    || candidate.getLayoutScore().getFinalScore()
                    > best.getLayoutScore().getFinalScore()) {

                best = candidate;
            }
        }

        if (best == null) {
            throw new IllegalStateException("All candidates have null scores.");
        }

        return best;
    }
}