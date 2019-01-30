package com.awk.featr.controllers;

import com.awk.featr.configuration.RepositoryConfiguration;
import com.awk.featr.model.registries.FeatureRegistry;
import com.awk.featr.services.FeatureService;
import com.awk.featr.model.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.awk.featr.services.RepositoryService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;


@CrossOrigin(origins = "*")
@RestController
public class FeatureController {

    private RepositoryConfiguration config;
    private RepositoryService repoService;
    private FeatureService featureService;
    private final FeatureRegistry featureRegistry;

    @Autowired
    public FeatureController(RepositoryConfiguration config, RepositoryService repoSvc, FeatureService featureSvc, FeatureRegistry featureRegistry ) {
        this.config = config;
        this.repoService = repoSvc;
        this.featureService = featureSvc;
        this.featureRegistry = requireNonNull(featureRegistry);
    }


    @GetMapping("/features/list")
    public @ResponseBody List<Feature> listFeatures(@RequestParam("maxSize") Optional<Long> maxOptionalSize) {
        Long maxSize = maxOptionalSize.orElse(10L);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeatures( " + maxSize + " )");

        return featureService.getFeatures(maxSize);
    }

    @GetMapping("/features/listfiles")
    public @ResponseBody List<String> listFeaturesAsStrings() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeaturesAsStrings()");

        return repoService.listFeaturesAsStrings(config);
    }
}
