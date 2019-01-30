package com.awk.featr.model;

import sun.misc.Regexp;

import java.nio.file.Path;
import java.util.Collection;

public class FeatureFile {
    private TestType testType;

    private TestLevel testLevel;

    private TestFileType testFileType;

    private Collection<Path> filePaths;

    private Regexp filesReqExp;
}
