package com.awk.featr.model.builder;

import com.awk.featr.model.gherkin.Background;
import com.awk.featr.model.gherkin.Step;

import java.util.ArrayList;
import java.util.List;

public class BackgroundBuilder {

    private String name;
    private List<Step> steps;
    private String description;

    public BackgroundBuilder(String name) {
        this.name = name;
        this.steps = new ArrayList<>();
        this.description = "";
    }

    public BackgroundBuilder withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public BackgroundBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Background build() {
        return new Background(name, steps, description);
    }
}