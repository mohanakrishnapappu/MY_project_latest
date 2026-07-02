package com.structurax.design.ai.constraint;

import com.structurax.design.ai.rules.RoomRule;
import com.structurax.design.ai.rules.RoomRuleEngine;
import org.springframework.stereotype.Component;

@Component
public class AIConstraintEngine {

    private final RoomRuleEngine ruleEngine;

    public AIConstraintEngine(RoomRuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public RoomRule getRule(String roomType) {
        return ruleEngine.getRule(roomType);
    }

    public boolean roomRequiresSunlight(String roomType) {

        RoomRule rule = ruleEngine.getRule(roomType);

        return rule != null && rule.isSunlightRequired();
    }

    public boolean roomRequiresVentilation(String roomType) {

        RoomRule rule = ruleEngine.getRule(roomType);

        return rule != null && rule.isVentilationRequired();
    }

    public boolean roomRequiresPrivacy(String roomType) {

        RoomRule rule = ruleEngine.getRule(roomType);

        return rule != null && rule.isPrivacyRequired();
    }
}