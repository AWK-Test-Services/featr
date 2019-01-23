package com.awk.featr.controllers;

import com.awk.featr.services.FeatureService;
import com.awk.featr.model.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.awk.featr.services.RepositoryService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
public class FeatureController {

    private RepositoryService repoService;
    private FeatureService featureService;

    @Autowired
    public FeatureController( RepositoryService repoSvc, FeatureService featureSvc ) {
        this.repoService = repoSvc;
        this.featureService = featureSvc;
    }

    @GetMapping("/features")
    public List<Feature> listFeatures() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeatures()");
        return repoService.listFeatureFiles().stream()
                .map( path -> featureService.getFeature(path) )
                .collect(Collectors.toList());
    }

    @GetMapping("/feature-files")
    public List<String> listFeaturesAsStrings() {
        return repoService.listFeaturesAsStrings();
    }
}
