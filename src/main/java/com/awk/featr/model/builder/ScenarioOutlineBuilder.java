package com.awk.featr.model.builder;

import com.awk.featr.model.gherkin.Example;
import com.awk.featr.model.gherkin.ScenarioOutline;
import com.awk.featr.model.gherkin.Step;
import com.awk.featr.model.gherkin.TableRow;

import java.util.ArrayList;
import java.util.List;

public class ScenarioOutlineBuilder {

    private String name;
    private List<Step> steps;
    private String description;
    private TableRow header;
    private List<TableRow> examples;

    public ScenarioOutlineBuilder(String name, TableRow header) {
        this.name = name;
        this.steps = new ArrayList<>();
        this.description = "";
        this.header = header;
        this.examples = new ArrayList<>();
    }

    public ScenarioOutlineBuilder withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public ScenarioOutlineBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ScenarioOutlineBuilder withExamples(List<TableRow> examples) {
        this.examples = examples;
        return this;
    }

    public ScenarioOutline build() {
        return new ScenarioOutline(name, description, steps, header, examples);
    }
}