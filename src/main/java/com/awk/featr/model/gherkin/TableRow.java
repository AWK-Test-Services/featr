package com.awk.featr.model.gherkin;

import java.util.List;

public class TableRow {
    private final List<String> values;

    public TableRow( List<String> values ) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
