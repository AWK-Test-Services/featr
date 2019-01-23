package com.awk.featr.controllers;

import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages.GherkinDocument;
import io.cucumber.messages.Messages;
import io.cucumber.messages.Messages.Wrapper;
import com.awk.featr.model.Feature;
import com.awk.featr.model.Scenario;
import com.awk.featr.model.Step;
import com.awk.featr.model.builder.FeatureBuilder;
import com.awk.featr.model.builder.FeatureChildBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.awk.featr.services.RepositoryService;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;


@CrossOrigin(origins = "*")
@RestController
public class FeatureController {

    private RepositoryService repoService;

    @Autowired
    public FeatureController( RepositoryService repoSvc ) {
        this.repoService = repoSvc;
    }

    @GetMapping("/feature-files")
    public List<String> listFeaturesAsStrings() {
        List<String> featureFiles = new ArrayList<>();
        try {
            URI localRepoUri = repoService.getLocalRepoPath().toURI();
            featureFiles = repoService.listFeatureFiles().stream()
                .map(path -> localRepoUri.relativize(path.toUri()).getPath())
                .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return featureFiles;
    }

    @GetMapping("/features")
    public List<Feature> listFeatures() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"listFeatures()");
        return repoService.listFeatureFiles().stream()
                .map( path -> getFeature(path) )
                .collect(Collectors.toList());
    }

    private Feature getFeature(Path featureFile) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"getFeature( " + featureFile.toString() + " )");
        List<Wrapper> messages = Gherkin.fromPaths(singletonList(featureFile.toString()), false, true, false);

        // Get the AST
        GherkinDocument gherkinDocument = messages.get(0).getGherkinDocument();

        // Get the Feature node of the AST
        Messages.Feature feature =  gherkinDocument.getFeature();

        FeatureBuilder featureBuilder = new FeatureBuilder(feature.getName())
                .withDescription(feature.getDescription())
                .withScenarios(convertScenarios(feature.getChildrenList()))
                .withFile(featureFile);

        return featureBuilder.build();
    }

    private List<Scenario> convertScenarios(List<Messages.FeatureChild> featureChildren) {
        return featureChildren.stream()
        .map( child -> convertScenario(child) )
        .collect(Collectors.toList());
    }

    private Scenario convertScenario(Messages.FeatureChild featureChild ) {
        Step testStep = new Step("Step text");
        ArrayList<Step> stepList = new ArrayList<>();
        stepList.add(testStep);

        FeatureChildBuilder scenarioBuilder = new FeatureChildBuilder( featureChild.getScenario().getName() );
        scenarioBuilder.withSteps(stepList);

        return scenarioBuilder.build();
    }
}
