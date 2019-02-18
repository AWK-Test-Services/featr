package com.awk.featr.services;

import com.awk.featr.model.gherkin.Feature;

import java.nio.file.Path;

public interface FeatureFileTypeService {
    Feature readFeature(Path featureFile);
}
