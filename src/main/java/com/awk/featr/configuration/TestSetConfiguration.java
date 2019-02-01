package com.awk.featr.configuration;

import com.awk.featr.model.TestFileType;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;

public class TestSetConfiguration {
    private String repoConfigId;

    private TestFileType testFileType;

    private String reqExp;

    private TestType type;

    private TestLevel level;

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

    public String getReqExp() {
        return reqExp;
    }

    public void setReqExp(String reqExp) {
        this.reqExp = reqExp;
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
