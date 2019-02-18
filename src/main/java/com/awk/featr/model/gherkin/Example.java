package com.awk.featr.model.gherkin;

import java.util.List;

public class Example {
    private final String name;
    private final String keyword;
    private final String description;
    private final TableRow header;
    private final List<TableRow> examples;

    public Example(String name, String keyword, String description, TableRow header, List<TableRow> examples) {
        this.name = name;
        this.keyword = keyword;
        this.description = description;
        this.header = header;
        this.examples = examples;
    }

    public String getName() {
        return name;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getDescription() {
        return description;
    }

    public TableRow getHeader() { return header; }

    public List<TableRow> getExamples() {
        return examples;
    }
}
