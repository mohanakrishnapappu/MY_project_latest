package com.structurax.design.rules;

import com.structurax.design.model.GeneratedLayout;

public interface LayoutRule {

    boolean validate(GeneratedLayout layout);

    String getRuleName();

    String getFailureMessage();

}