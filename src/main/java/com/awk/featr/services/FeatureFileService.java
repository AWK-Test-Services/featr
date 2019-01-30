package com.awk.featr.services;

import com.awk.featr.configuration.RepositoryConfiguration;
import com.awk.featr.model.converters.FeatureFileConverter;
import com.awk.featr.model.registries.FeatureRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

@Service
public class FeatureFileService {

    private final FeatureRegistry featureRegistry;
    private final RepositoryService repoService;

    @Autowired
    public FeatureFileService(FeatureRegistry featureRegistry, RepositoryService repoService) {
        this.featureRegistry = requireNonNull(featureRegistry);
        this.repoService = requireNonNull(repoService);
    }

    public void readFeatureFiles(RepositoryConfiguration config) {
        parseFeatureFiles(repoService.listFeatureFiles(config));
    }

    private void parseFeatureFiles(List<Path> featureFiles) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"parseFeatureFiles( " + featureFiles.size() + " )");
        featureFiles.stream()
                .forEach( path -> featureRegistry.add(FeatureFileConverter.getFeature(path)) );
    }
}
