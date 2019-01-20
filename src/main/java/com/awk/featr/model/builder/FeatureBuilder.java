package com.awk.featr.model.builder;

import com.awk.featr.model.Feature;
import com.awk.featr.model.Scenario;

import java.nio.file.Path;
import java.util.List;

public class FeatureBuilder {

    private String name;
    private String description;
    private List<Scenario> scenarios;
    private Path file;

    public FeatureBuilder(String name) {
        this.name = name;
    }

    public FeatureBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FeatureBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FeatureBuilder withScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
        return this;
    }

    public FeatureBuilder withFile(Path file) {
        this.file = file;
        return this;
    }

    public Feature build() {
        return new Feature(name, description, scenarios, file);
    }
}
