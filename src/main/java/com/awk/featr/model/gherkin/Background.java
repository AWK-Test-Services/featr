package com.awk.featr.model.gherkin;

import java.util.List;

public class Background extends ScenarioDefinition {

    public Background(String name, List<Step> steps, String description) {
        super(name, description, steps);
    }
}
