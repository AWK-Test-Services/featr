package com.awk.featr.model.registries;

import com.awk.featr.model.Feature;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Component
public class FeatureRegistry {
    private Map<String, Feature> registry;

    public FeatureRegistry() {
        this.registry = new HashMap<>();
    }

    public Feature getFeature(String id) {
        return registry.get(id);
    }

    public Feature add(Feature feature) {
        requireNonNull(feature);
        return registry.put(feature.getId(), feature);
    }

    public Collection<Feature> getFeatures() {
        return registry.values();
    }
}
