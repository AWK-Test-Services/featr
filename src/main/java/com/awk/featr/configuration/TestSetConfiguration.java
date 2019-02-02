package com.awk.featr.configuration;

import com.awk.featr.model.TestFileType;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;

import static java.util.Objects.requireNonNull;

public class TestSetConfiguration {
    private String id;

    private String repoConfigId;

    private TestFileType testFileType;

    private String regExp;

    private TestType type;

    private TestLevel level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = requireNonNull(id);
    }

    public String getRepoConfigId() {
        return repoConfigId;
    }

    public void setRepository(String repoConfigId) {
        this.repoConfigId = repoConfigId;
    }

    public TestFileType getTestFileType() {
        return testFileType;
    }

    public void setTestFileType(TestFileType testFileType) {
        this.testFileType = testFileType;
    }

    public String getRegExp() {
        return regExp;
    }

    public void setRegExp(String regExp) {
        this.regExp = regExp;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public TestLevel getLevel() {
        return level;
    }

    public void setLevel(TestLevel level) {
        this.level = level;
    }
}
