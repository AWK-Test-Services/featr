package com.awk.featr.controllers;

import com.awk.featr.configuration.RepositoryConfiguration;
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


@CrossOrigin(origins = "*")
@RestController
public class FeatureController {

    private RepositoryConfiguration config;
    private RepositoryService repoService;
    private FeatureService featureService;

    @Autowired
    public FeatureController(RepositoryConfiguration config, RepositoryService repoSvc, FeatureService featureSvc ) {
        this.config = config;
        this.repoService = repoSvc;
        this.featureService = featureSvc;
    }

    @GetMapping("/features/list")
    public @ResponseBody List<Feature> listFeatures(@RequestParam("maxSize") Optional<Long> maxSize) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeatures( " + maxSize + " )");
        return repoService.listFeatureFiles( config, maxSize ).stream()
                .map( path -> featureService.getFeature(path) )
                .collect(Collectors.toList());
    }

    @GetMapping("/features/listfiles")
    public @ResponseBody List<String> listFeaturesAsStrings() {
        return repoService.listFeaturesAsStrings(config);
    }
}
