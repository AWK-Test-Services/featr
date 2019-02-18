package com.awk.featr.model.builder;

import com.awk.featr.model.gherkin.Step;
import com.awk.featr.model.gherkin.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenarioBuilder {

    private String name;
    private List<Step> steps;
    private String description;

    public ScenarioBuilder(String name) {
        this.name = name;
        this.steps = new ArrayList<>();
        this.description = "";
    }

    public ScenarioBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ScenarioBuilder withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public ScenarioBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Scenario build() {
        return new Scenario(name, description, steps);
    }
}