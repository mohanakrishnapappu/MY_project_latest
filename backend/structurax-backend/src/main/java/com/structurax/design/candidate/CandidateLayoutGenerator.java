package com.structurax.design.candidate;

import com.structurax.design.model.GeneratedLayout;
import com.structurax.design.model.LayoutCandidate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateLayoutGenerator {

    public List<LayoutCandidate> generateCandidates(
            GeneratedLayout baseLayout,
            int totalCandidates
    ) {

        List<LayoutCandidate> candidates = new ArrayList<>();

        for (int i = 1; i <= totalCandidates; i++) {

            LayoutCandidate candidate = new LayoutCandidate();

            candidate.setCandidateNumber(i);

            candidate.setRooms(new ArrayList<>(baseLayout.getRooms()));

            candidate.setWalls(new ArrayList<>(baseLayout.getWalls()));

            candidate.setDoors(new ArrayList<>(baseLayout.getDoors()));

            candidate.setWindows(new ArrayList<>(baseLayout.getWindows()));

            candidate.setLayoutScore(baseLayout.getLayoutScore());

            candidates.add(candidate);
        }

        return candidates;
    }
}