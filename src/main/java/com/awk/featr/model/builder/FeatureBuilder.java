package com.awk.featr.model.builder;

import com.awk.featr.model.Feature;
import com.awk.featr.model.Scenario;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FeatureBuilder {

    private String id;
    private String name;
    private String description;
    private List<Scenario> scenarios;
    private Path file;
    private List<TestLevel> testLevels;
    private List<TestType> testTypes;

    public FeatureBuilder(String name, Path file) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = "";
        this.scenarios = new ArrayList<>();
        this.file = file;
        this.testLevels = new ArrayList<>();
        this.testTypes = new ArrayList<>();
    }

    public FeatureBuilder withId(String id) {
        this.id = id;
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

    public FeatureBuilder withTestLevels(List<TestLevel> levels) {
        this.testLevels = levels;
        return this;
    }

    public FeatureBuilder withTestTypes(List<TestType> types) {
        this.testTypes = types;
        return this;
    }

    public Feature build() {
        return new Feature(id, name, description, scenarios, file, testLevels, testTypes);
    }
}
