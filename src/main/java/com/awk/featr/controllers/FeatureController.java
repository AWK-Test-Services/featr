package com.awk.featr.controllers;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.configuration.TestSetConfiguration;
import com.awk.featr.model.registries.FeatureRegistry;
import com.awk.featr.services.FeatureService;
import com.awk.featr.model.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.awk.featr.services.RepositoryService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;


@CrossOrigin(origins = "*")
@RestController
public class FeatureController {

    private FeatrConfiguration configuration;
    private RepositoryService repoService;
    private FeatureService featureService;
    private final FeatureRegistry featureRegistry;

    @Autowired
    public FeatureController(FeatrConfiguration config, RepositoryService repoSvc, FeatureService featureSvc, FeatureRegistry featureRegistry ) {
        this.configuration = config;
        this.repoService = repoSvc;
        this.featureService = featureSvc;
        this.featureRegistry = requireNonNull(featureRegistry);
    }

    @GetMapping("/features/list")
    public @ResponseBody List<Feature> listFeatures(@RequestParam("testset") String testSetId,
                                                    @RequestParam("maxSize") Optional<Long> maxOptionalSize) {
        Long maxSize = maxOptionalSize.orElse(10L);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeatures( " + testSetId + ", " + maxSize + " )");

        return featureService.getFeatures(maxSize);
    }

    @GetMapping("/features/settings")
    public @ResponseBody Collection<TestSetConfiguration> listSettings() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listSettings()");

        return configuration.getTestSets();
    }

    @GetMapping("/features/listfiles")
    public @ResponseBody List<String> listFeaturesAsStrings(@RequestParam("repository") String repoId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeaturesAsStrings( " + repoId + " )");

        return repoService.listFeaturesAsStrings(configuration.getRepositories().get(repoId));
    }
}
