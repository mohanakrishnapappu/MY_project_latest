package com.structurax.design.rules;

import com.structurax.design.model.GeneratedLayout;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LayoutRuleEngine {

    private final List<LayoutRule> rules;

    public LayoutRuleEngine(List<LayoutRule> rules) {
        this.rules = rules;
    }

    public List<String> validate(GeneratedLayout layout) {

        List<String> failures = new ArrayList<>();

        for (LayoutRule rule : rules) {

            if (!rule.validate(layout)) {
                failures.add(
                        rule.getRuleName()
                                + " : "
                                + rule.getFailureMessage()
                );
            }

        }

        return failures;

    }

}