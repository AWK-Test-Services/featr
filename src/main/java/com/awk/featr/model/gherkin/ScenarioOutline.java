package com.awk.featr.model.gherkin;

import java.util.List;

public class ScenarioOutline extends ScenarioDefinition {
    private final TableRow header;
    private final List<TableRow> examples;

    public ScenarioOutline(String name, String description, List<Step> steps, TableRow header, List<TableRow> examples ) {
        super(name, description, steps);
        this.header = header;
        this.examples = examples;
    }

    public TableRow getHeader() { return header; }
    public List<TableRow> getExamples() { return examples; }
}
