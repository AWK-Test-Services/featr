package com.awk.featr.controllers;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.configuration.TestSetConfiguration;
import com.awk.featr.services.FeatureService;
import com.awk.featr.model.gherkin.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.awk.featr.services.RepositoryService;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;


@CrossOrigin(origins = "*")
@RestController
public class FeatureController {

    private FeatrConfiguration configuration;
    private RepositoryService repoService;
    private FeatureService featureService;

    @Autowired
    public FeatureController(FeatrConfiguration config, RepositoryService repoSvc, FeatureService featureSvc ) {
        this.configuration = requireNonNull(config);
        this.repoService = requireNonNull(repoSvc);
        this.featureService = requireNonNull(featureSvc);
    }

    @GetMapping("/features/list")
    public @ResponseBody List<Feature> listFeatures(@RequestParam("testset") String testSetId,
                                                    @RequestParam(value="maxSize", required = false) Long maxOptionalSize) {
        Long maxSize = maxOptionalSize==null ? 10L : maxOptionalSize;
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeatures( " + testSetId + ", " + maxSize + " )");

        return featureService.getFeatures(maxSize);
    }

    @GetMapping("/features/details")
    public @ResponseBody Feature getFeature(@RequestParam("featureId") String featureId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"getFeature( " + featureId + " )");

        return featureService.getFeature(requireNonNull(featureId));
    }

    @GetMapping("/features/settings")
    public @ResponseBody Collection<TestSetConfiguration> listSettings() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listSettings()");

        return configuration.getTestSets();
    }

    @GetMapping("/features/listfiles")
    public @ResponseBody List<String> listFeaturesAsStrings() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeaturesAsStrings()");
        return configuration.getTestSets().stream()
                .flatMap( testSetConfig ->
                        repoService.listFeaturesAsStrings( testSetConfig.getRepoConfigId(), testSetConfig.getRegExp() )
                                .stream() )
                .collect(Collectors.toList());
    }
}
