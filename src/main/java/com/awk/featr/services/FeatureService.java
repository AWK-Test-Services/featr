package com.awk.featr.services;

import com.awk.featr.model.gherkin.Feature;
import com.awk.featr.model.registries.FeatureRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class FeatureService {

    private final FeatureRegistry featureRegistry;

    @Autowired
    public FeatureService(FeatureRegistry featureRegistry) {
        this.featureRegistry = requireNonNull(featureRegistry);
    }

    public List<Feature> getFeatures(Long maxSize) {
        return featureRegistry.getFeatures().stream()
                .limit(maxSize)
                .collect(Collectors.toList());
    }

    public Feature getFeature(String featureId) {
        return featureRegistry.getFeature(requireNonNull(featureId));
    }
}
