package com.awk.featr.configuration;

import com.awk.featr.model.Feature;
import com.awk.featr.model.TestLevel;
import com.awk.featr.model.TestType;
import com.awk.featr.services.RepositoryException;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Validated
@Configuration
@ConfigurationProperties(prefix="featr")
public class FeatrConfiguration {

    private String version;

    @Length(max = 10, min = 5)
    private String adminPassword;

    private List<TestType> testTypes = new ArrayList<>();
    private List<TestLevel> testLevels = new ArrayList<>();

    private Map<String, RepositoryConfiguration> repositories = new HashMap<>();

    private Map<String, TestSetConfiguration> testSets = new HashMap<>();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public List<TestType> getTestTypes() {
        return testTypes;
    }

    public List<TestLevel> getTestLevels() {
        return testLevels;
    }

    public Map<String, RepositoryConfiguration> getRepositories() {
        return repositories;
    }

    public RepositoryConfiguration getRepository(String repoId) throws RepositoryException {
        if( !repositories.containsKey( requireNonNull(repoId) )) {
            throw new RepositoryException("Repository " + repoId + " not found. Available repositories are " + repositories.keySet().toString());
        }

        return repositories.get(repoId);
    }

    public Map<String, TestSetConfiguration> getTestSets() {
        return testSets;
    }

    public Collection<TestSetConfiguration> getTestSetCollection() {
        return testSets.values();
    }
}
