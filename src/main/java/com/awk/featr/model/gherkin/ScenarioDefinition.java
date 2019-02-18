package com.awk.featr.model.gherkin;

import java.util.List;

public abstract class ScenarioDefinition {
    private final String name;
    private final List<Step> steps;
    private final String description;

    ScenarioDefinition(String name, String description, List<Step> steps) {
        this.name = name;
        this.description = description;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
