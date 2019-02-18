package com.awk.featr.model.registries;

import com.awk.featr.model.gherkin.Feature;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Component
public class FeatureRegistry {
    private Map<String, Feature> featureRegistry;
    private Map<String, Feature> filePathRegistry;

    public FeatureRegistry() {
        this.featureRegistry = new HashMap<>();
        this.filePathRegistry = new HashMap<>();
    }

    public Feature getFeature(String id) {
        return featureRegistry.get(requireNonNull(id));
    }

    public Feature getFeature(Path filePath) {
        return filePathRegistry.get(requireNonNull(filePath.toString()));
    }

    public Feature add(Feature feature) {
        requireNonNull(feature);
        String filePathName = feature.getFile().toString();
        Feature oldFeature = filePathRegistry.get(filePathName);
        if (oldFeature != null ) {
            removeFeature(oldFeature);
        }

        filePathRegistry.put(filePathName, feature);
        return featureRegistry.put(feature.getId(), feature);
    }

    private void removeFeature(Feature oldFeature) {
        filePathRegistry.remove(oldFeature.getFile().toString());
        featureRegistry.remove(oldFeature.getId());
    }

    public Collection<Feature> getFeatures() {
        return featureRegistry.values();
    }
}
