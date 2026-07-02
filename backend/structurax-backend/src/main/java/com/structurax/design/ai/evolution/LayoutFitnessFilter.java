package com.structurax.design.ai.evolution;

import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LayoutFitnessFilter {

    public List<LayoutCandidate> selectTopCandidates(
            List<LayoutCandidate> candidates,
            int survivorCount) {

        return candidates.stream()
                .sorted(
                        Comparator.comparingDouble(
                                (LayoutCandidate c) ->
                                        c.getLayoutScore().getFinalScore())
                                .reversed()
                )
                .limit(survivorCount)
                .collect(Collectors.toList());
    }
}