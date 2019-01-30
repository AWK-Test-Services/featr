package com.awk.featr.model;

import java.nio.file.Path;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Feature {
    private final String id;
    private final String name;
    private final String description;
    private final List<Scenario> scenarios;

    private final Path file; // List<Path> files;
    private final List<TestLevel> testLevels;
    private final List<TestType> testTypes;

    public Feature(String id, String name, String description, List<Scenario> scenarios, Path file, List<TestLevel> testLevels, List<TestType> testTypes) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.description = requireNonNull(description);
        this.scenarios = requireNonNull(scenarios);
        this.file = requireNonNull(file);
        this.testLevels = requireNonNull(testLevels);
        this.testTypes = requireNonNull(testTypes);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public Path getFile() {
        return file;
    }

    public List<TestLevel> getTestLevels() {
        return testLevels;
    }

    public List<TestType> getTestTypes() {
        return testTypes;
    }
}
