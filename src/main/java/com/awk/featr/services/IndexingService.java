package com.awk.featr.services;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.configuration.RepositoryConfiguration;
import com.awk.featr.configuration.TestSetConfiguration;
import com.awk.featr.model.registries.FeatureRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

@Service
public class IndexingService {

    private final FeatrConfiguration featrConfiguration;
    private final FeatureRegistry featureRegistry;
    private final FeatureFileService featureFileService;

    @Autowired
    public IndexingService(FeatrConfiguration featrConfiguration, FeatureRegistry featureRegistry, FeatureFileService featureFileService) {
        this.featrConfiguration = requireNonNull(featrConfiguration);
        this.featureRegistry = requireNonNull(featureRegistry);
        this.featureFileService = requireNonNull(featureFileService);
    }

    public void index() {
        featrConfiguration.getTestSets()
                .forEach(this::indexTestSet);
    }

    private void indexTestSet(TestSetConfiguration tsConfig) {
            featureFileService.readFeatures(tsConfig)
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(featureRegistry::add);
    }
}
