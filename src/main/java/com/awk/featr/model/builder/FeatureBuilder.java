package com.awk.featr.model.builder;

import com.awk.featr.model.gherkin.Background;
import com.awk.featr.model.gherkin.Feature;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;
import com.awk.featr.model.gherkin.ScenarioDefinition;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class FeatureBuilder {

    private String id;
    private String name;
    private String description;
    private Background background;
    private List<ScenarioDefinition> scenarioDefinitions;
    private Path file;
    private List<TestLevel> testLevels;
    private List<TestType> testTypes;

    public FeatureBuilder(String name, Path file) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = "";
        this.background = null;
        this.scenarioDefinitions = new ArrayList<>();
        this.file = file;
        this.testLevels = new ArrayList<>();
        this.testTypes = new ArrayList<>();
    }

    public FeatureBuilder withId(String id) {
        this.id = requireNonNull(id);
        return this;
    }

    public FeatureBuilder withDescription(String description) {
        this.description = requireNonNull(description);
        return this;
    }

    public FeatureBuilder withBackground(Background background) {
        this.background = requireNonNull(background);
        return this;
    }

    public FeatureBuilder withScenarioDefinitions(List<ScenarioDefinition> scenarios) {
        this.scenarioDefinitions = requireNonNull(scenarios);
        return this;
    }

    public FeatureBuilder withTestLevels(List<TestLevel> levels) {
        this.testLevels = requireNonNull(levels);
        return this;
    }

    public FeatureBuilder withTestTypes(List<TestType> types) {
        this.testTypes = requireNonNull(types);
        return this;
    }

    public Feature build() {
        return new Feature(id, name, description, background, scenarioDefinitions, file, testLevels, testTypes);
    }
}
