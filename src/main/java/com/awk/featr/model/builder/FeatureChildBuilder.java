package com.awk.featr.model.builder;

import com.awk.featr.model.Step;
import com.awk.featr.model.Scenario;

import java.util.ArrayList;
import java.util.List;

public class FeatureChildBuilder {

    private String name;
    private List<Step> steps;
    private String description;

    public FeatureChildBuilder(String name) {
        this.name = name;
        this.steps = new ArrayList<>();
        this.description = "";
    }

    public FeatureChildBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FeatureChildBuilder withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public FeatureChildBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Scenario build() {
        return new Scenario(name, steps, description);
    }
}