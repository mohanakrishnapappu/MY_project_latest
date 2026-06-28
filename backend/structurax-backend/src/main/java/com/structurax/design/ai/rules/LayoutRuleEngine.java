package com.structurax.design.ai.rules;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LayoutRuleEngine {

    private final Map<String, RoomRule> rules =
            new HashMap<>();

    @PostConstruct
    public void initializeRules() {

        rules.put("BEDROOM", bedroomRule());

        rules.put("KITCHEN", kitchenRule());

        rules.put("HALL", hallRule());

        rules.put("BATHROOM", bathroomRule());

    }

    public RoomRule getRule(String roomType) {

        return rules.get(roomType.toUpperCase());

    }

    public List<RoomRule> getAllRules() {

        return rules.values().stream().toList();

    }

    private RoomRule bedroomRule() {

        RoomRule rule = new RoomRule();

        rule.setRoomType("BEDROOM");

        rule.setMinimumWidth(10);

        rule.setMinimumLength(12);

        rule.setPreferredArea(120);

        rule.setSunlightRequired(true);

        rule.setVentilationRequired(true);

        rule.setPrivacyRequired(true);

        rule.getPreferredAdjacentRooms().add("BATHROOM");

        rule.getForbiddenAdjacentRooms().add("KITCHEN");

        return rule;

    }

    private RoomRule kitchenRule() {

        RoomRule rule = new RoomRule();

        rule.setRoomType("KITCHEN");

        rule.setMinimumWidth(8);

        rule.setMinimumLength(10);

        rule.setPreferredArea(80);

        rule.setSunlightRequired(true);

        rule.setVentilationRequired(true);

        rule.setPrivacyRequired(false);

        rule.getPreferredAdjacentRooms().add("DINING");

        return rule;

    }

    private RoomRule hallRule() {

        RoomRule rule = new RoomRule();

        rule.setRoomType("HALL");

        rule.setMinimumWidth(15);

        rule.setMinimumLength(20);

        rule.setPreferredArea(300);

        rule.setSunlightRequired(true);

        return rule;

    }

    private RoomRule bathroomRule() {

        RoomRule rule = new RoomRule();

        rule.setRoomType("BATHROOM");

        rule.setMinimumWidth(6);

        rule.setMinimumLength(8);

        rule.setPreferredArea(48);

        rule.setVentilationRequired(true);

        return rule;

    }

}