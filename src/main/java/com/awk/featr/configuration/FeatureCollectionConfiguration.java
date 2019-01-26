package com.awk.featr.configuration;

import java.nio.file.Path;

public class FeatureCollectionConfiguration {
    private Path baseDir;

    private String fileReqExp;

    public FeatureCollectionConfiguration(Path baseDir, String fileReqExp) {
        this.baseDir = baseDir;
        this.fileReqExp = fileReqExp;
    }

    public Path getBaseDir() {
        return baseDir;
    }

    public String getFileReqExp() {
        return fileReqExp;
    }
}
