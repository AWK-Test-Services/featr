package com.awk.featr.services;

import com.awk.featr.model.Feature;
import com.awk.featr.model.Scenario;
import com.awk.featr.model.Step;
import com.awk.featr.model.builder.FeatureBuilder;
import com.awk.featr.model.builder.FeatureChildBuilder;
import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
public class FeatureService {

    public FeatureService() {
    }

    public Feature getFeature(Path featureFile) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"getFeature( " + featureFile.toString() + " )");
        List<Messages.Wrapper> messages = Gherkin.fromPaths(singletonList(featureFile.toString()), false, true, false);

        // Get the AST
        Messages.GherkinDocument gherkinDocument = messages.get(0).getGherkinDocument();

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
