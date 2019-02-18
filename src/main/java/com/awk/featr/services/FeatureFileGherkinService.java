package com.awk.featr.services;

import com.awk.featr.model.builder.*;
import com.awk.featr.model.gherkin.*;
import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.Optional.empty;

public class FeatureFileGherkinService implements FeatureFileTypeService {

    @Override
    public Feature readFeature(Path featureFile) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"readFeature( " + featureFile.toString() + " )");
        return getFeature(featureFile);
    }

    private static Feature getFeature(Path featureFile) {
        List<Messages.Wrapper> messages = Gherkin.fromPaths(singletonList(featureFile.toString()), false, true, false);

        // Get the Abstract Syntax Tree (AST)
        Messages.GherkinDocument gherkinDocument = messages.get(0).getGherkinDocument();

        // Get the Feature node of the AST
        Messages.Feature feature =  gherkinDocument.getFeature();
        List<ScenarioDefinition> scenarioDefinitions = convertScenarioDefinitions(feature.getChildrenList());

        FeatureBuilder featureBuilder = new FeatureBuilder(feature.getName(), featureFile)
                .withDescription(feature.getDescription())
                .withScenarioDefinitions(
                    scenarioDefinitions.stream()
                        .filter( scenarioDefinition -> Scenario.class.isInstance(scenarioDefinition)
                                                       || ScenarioOutline.class.isInstance(scenarioDefinition) )
                        .collect(Collectors.toList()) );

        Optional<Background> background = scenarioDefinitions.stream()
                .filter( Background.class::isInstance )
                .map( scenarioDefinition -> (Background) scenarioDefinition)
                .findFirst();
        background.ifPresent(featureBuilder::withBackground);

        return featureBuilder.build();
    }

    private static List<ScenarioDefinition> convertScenarioDefinitions(List<Messages.FeatureChild> featureChildren) {
        return featureChildren.stream()
                .map(FeatureFileGherkinService::convertScenarioDefinition)
                .collect(Collectors.toList());
    }

    private static ScenarioDefinition convertScenarioDefinition(Messages.FeatureChild featureChild ) {

        if ( featureChild.hasBackground() ) {
            return convertBackground(featureChild.getBackground());
        }

        if ( ! featureChild.getScenario().getName().isEmpty() ) {
            Messages.Scenario scenario = featureChild.getScenario();
            if ( scenario.getExamplesCount() != 0 ) {
                return convertScenarioOutline( scenario );
            } // else
            return convertScenario(scenario);
        }

        //TODO throw exception?
        return null;
    }

    private static Background convertBackground(Messages.Background background) {
        BackgroundBuilder backgroundBuilder = new BackgroundBuilder( background.getName() )
                .withDescription(background.getDescription())
                .withSteps(convertSteps(background.getStepsList()));

        return backgroundBuilder.build();
    }

    private static Scenario convertScenario(Messages.Scenario scenario) {
        ScenarioBuilder scenarioBuilder = new ScenarioBuilder(scenario.getName())
                .withSteps(convertSteps(scenario.getStepsList()))
                .withDescription(scenario.getDescription());

        return scenarioBuilder.build();
    }

    private static ScenarioOutline convertScenarioOutline(Messages.Scenario scenarioOutline) {
        List<Example> examples = convertExamples(scenarioOutline.getExamplesList());
        TableRow header = examples.isEmpty() ? new TableRow( new ArrayList<>() ) : examples.get(0).getHeader();

                ScenarioOutlineBuilder scenarioOutlineBuilder = new ScenarioOutlineBuilder(scenarioOutline.getName(), header)
                .withSteps(convertSteps(scenarioOutline.getStepsList()))
                .withDescription(scenarioOutline.getDescription())
                .withExamples( examples.stream()
                        .filter( example -> ! example.getExamples().isEmpty() )
                        .map( example -> example.getExamples().get(0) )
                        .collect(Collectors.toList()));

        return scenarioOutlineBuilder.build();
    }

    private static List<Step> convertSteps(List<Messages.Step> stepsList) {
        return stepsList.stream()
                .map(FeatureFileGherkinService::convertStep)
                .collect(Collectors.toList());
    }

    private static Step convertStep(Messages.Step step) {
        return new Step( step.getText(), convertStepArgument(step) );
    }

    private static Optional<StepArgument> convertStepArgument(Messages.Step step) {
        Optional<StepArgument> argument = Optional.empty();
        if ( step.hasDataTable() ) {
            List<TableRow> arguments = convertTableRows( step.getDataTable().getRowsList() );
            argument = Optional.of(new DataTable(arguments));
        } else if ( step.hasDocString() ) {
            DocString docString = new DocString( step.getDocString().getContent() );
            argument = Optional.of(docString);
        }
        return argument;
    }

    private static List<Example> convertExamples(List<Messages.Examples> examples) {
        return examples.stream()
                .map(FeatureFileGherkinService::convertExample)
                .collect(Collectors.toList());
    }

    private static Example convertExample(Messages.Examples example) {
        TableRow header = convertTableRow(example.getTableHeader());
        ExampleBuilder exampleBuilder = new ExampleBuilder(example.getName(), example.getKeyword(), header)
                .withDescription(example.getDescription())
                .withExamples( convertTableRows( example.getTableBodyList()));

        return exampleBuilder.build();
    }

    private static List<TableRow> convertTableRows(List<Messages.TableRow> tableRows) {
        return tableRows.stream()
                .map(FeatureFileGherkinService::convertTableRow)
                .collect(Collectors.toList());
    }

    private static TableRow convertTableRow(Messages.TableRow tableRow) {
        return new TableRow(convertTableCells(tableRow.getCellsList()));
    }

    private static List<String> convertTableCells(List<Messages.TableCell> tableCells ) {
        return tableCells.stream()
                .map(FeatureFileGherkinService::convertTableCell)
                .collect(Collectors.toList());
    }

    private static String convertTableCell(Messages.TableCell tableCell ) {
        return tableCell.getValue();
    }
}
