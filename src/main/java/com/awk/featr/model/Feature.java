package com.awk.featr.model;

import java.nio.file.Path;
import java.util.List;

public class Feature {
    private final String name;
    private final String description;
    private final List<Scenario> scenarios;
    private final Path file;

    public Feature(String name, String description, List<Scenario> scenarios, Path file) {
        this.name = name;
        this.description = description;
        this.scenarios = scenarios;
        this.file = file;
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
}
