package com.awk.featr.services;

import com.awk.featr.configuration.TestSetConfiguration;
import com.awk.featr.model.Feature;
import com.awk.featr.model.TestFileType;
import com.awk.featr.model.registries.FeatureRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class FeatureFileService {

    private final RepositoryService repoService;

    private final Map<TestFileType, FeatureFileTypeService> featureFileTypeConverters;


    @Autowired
    public FeatureFileService(RepositoryService repoService) {
        this.repoService = requireNonNull(repoService);

        this.featureFileTypeConverters = new HashMap<>();
        this.featureFileTypeConverters.put(TestFileType.GHERKIN, new FeatureFileGherkinService());
        this.featureFileTypeConverters.put(TestFileType.JUNIT, new FeatureFileJUnitService());
        this.featureFileTypeConverters.put(TestFileType.JAVASCRIPT, new FeatureFileJavascriptService());
    }

    Collection<Feature> readFeatures(TestSetConfiguration tsConfig) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "readFeatures( " + tsConfig.toString() + " )");
        FeatureFileTypeService fileTypeConverter = getFileTypeConverter(tsConfig.getTestFileType());
        String fileRegExp = tsConfig.getRegExp();

        Collection<Path> featureFiles = repoService.listFeatureFiles(tsConfig.getRepoConfigId(), fileRegExp);
        return featureFiles.stream()
                .map(fileTypeConverter::readFeature)
                .collect(Collectors.toList());
        }

    private FeatureFileTypeService getFileTypeConverter(TestFileType testFileType) {
        return featureFileTypeConverters.get(testFileType);
    }

}
