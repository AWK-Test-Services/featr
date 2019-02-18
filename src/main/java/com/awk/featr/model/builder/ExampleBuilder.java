package com.awk.featr.model.builder;

import com.awk.featr.model.gherkin.Example;
import com.awk.featr.model.gherkin.TableRow;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ExampleBuilder {

    private String name;
    private String keyword;
    private String description;
    private TableRow header;
    private List<TableRow> examples;

    public ExampleBuilder(String name, String keyword, TableRow header) {
        this.name = requireNonNull(name);
        this.keyword = requireNonNull(keyword);
        this.description = "";
        this.header = requireNonNull(header);
        this.examples = new ArrayList<>();
    }

    public ExampleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ExampleBuilder withExamples(List<TableRow> examples) {
        this.examples = examples;
        return this;
    }

    public Example build() {
        return new Example(name, keyword, description, header, examples);
    }

}