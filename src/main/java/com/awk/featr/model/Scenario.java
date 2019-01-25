package com.awk.featr.model;

import java.util.List;

public class Scenario {
    private final String name;
    private final List<Step> steps;
    private final String description;

    public Scenario(String name, List<Step> steps, String description) {
        this.name = name;
        this.steps = steps;
        this.description = description;
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
