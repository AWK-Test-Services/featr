package com.awk.featr.services;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.configuration.RepositoryConfiguration;
import com.awk.featr.configuration.TestSetConfiguration;
import com.awk.featr.model.registries.FeatureRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class IndexingService {

    private final FeatrConfiguration featrConfiguration;

    private final FeatureFileService featureFileService;

    @Autowired
    public IndexingService(FeatrConfiguration featrConfiguration, FeatureFileService featureFileService) {
        this.featrConfiguration = requireNonNull(featrConfiguration);
        this.featureFileService = requireNonNull(featureFileService);
    }

    public void index() {
        featrConfiguration.getTestSets().stream()
                .forEach(this::indexTestSet);
    }

    private void indexTestSet(TestSetConfiguration tsConfig) {
        try {
            RepositoryConfiguration repositoryConfiguration = featrConfiguration.getRepository(tsConfig.getRepoConfigId());
            featureFileService.indexFeatureFiles(repositoryConfiguration);
        } catch (RepositoryException e) {
            // TODO we do want to report (but to whom?)
            e.printStackTrace();
        }
    }
}
