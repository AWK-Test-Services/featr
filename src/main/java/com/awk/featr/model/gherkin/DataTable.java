package com.awk.featr.model.gherkin;

import java.util.List;

public class DataTable extends StepArgument {
    private final List<TableRow> rows;

    public DataTable(List<TableRow> rows) {

        this.rows = rows;
    }

    public List<TableRow> getRows() {
        return rows;
    }
}
