package com.awk.featr.services;

import com.awk.featr.model.gherkin.Feature;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeatureFileJUnitService implements FeatureFileTypeService {

    @Override
    public Feature readFeature(Path featureFile) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"readFeature( " + featureFile.toString() + " )");
        return null;
    }
}
