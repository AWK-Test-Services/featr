package com.awk.featr.model;

import java.util.List;

public class Scenario {
    private final String name;
    private final List<Step> steps;

    public Scenario(String name, List<Step> steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
