    package com.structurax.design.optimizer;

import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PopulationManager {

    public List<LayoutCandidate> merge(

            List<LayoutCandidate> parents,

            List<LayoutCandidate> children

    ) {

        List<LayoutCandidate> population =
                new ArrayList<>();

        population.addAll(parents);

        population.addAll(children);

        return population;

    }

}