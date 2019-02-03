package com.awk.featr.services;

import com.awk.featr.model.Feature;
import com.awk.featr.model.Scenario;
import com.awk.featr.model.Step;
import com.awk.featr.model.builder.FeatureBuilder;
import com.awk.featr.model.builder.FeatureChildBuilder;
import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class FeatureFileJavascriptService implements FeatureFileTypeService {

    @Override
    public Feature readFeature(Path featureFile) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"readFeature( " + featureFile.toString() + " )");
        return null;
    }
}
