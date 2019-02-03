package com.awk.featr.services;

import com.awk.featr.configuration.FeatrConfiguration;
import com.awk.featr.configuration.RepositoryConfiguration;
import com.awk.featr.model.registries.FeatureRegistry;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class RepositoryService {

    private FeatureRegistry featureRegistry;

    private Map<String, RepositoryConfiguration> configurationMap;

    @Autowired
    public RepositoryService(FeatureRegistry featureRegistry, FeatrConfiguration featrConfiguration) {
        this.featureRegistry = requireNonNull(featureRegistry);
        this.configurationMap = requireNonNull(featrConfiguration).getRepositories();
    }

    public void cloneRepository(RepositoryConfiguration config) throws RepositoryException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "cloneRepository( " + config.getRepoLocation() + " )");

        try {
            Git.cloneRepository()
                    .setURI(config.getRepoLocation())
                    .setDirectory(config.getLocalRepoPath())
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            throw new RepositoryException("Error cloning repo " + config.getRepoLocation() + " to " + config.getLocalRepoPath() + ", " + e.getMessage(), e);
        }
    }

    public void deleteRepository(RepositoryConfiguration config) throws RepositoryException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "deleteRepository()");

        try {
            FileUtils.deleteDirectory(config.getLocalRepoPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RepositoryException("Error deleting repo at " + config.getLocalRepoPath() + ", " + e.getMessage(), e);
        }
    }

    public Collection<Path> listFeatureFiles(String repoConfigId) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles( " + repoConfigId + " )");
        RepositoryConfiguration repositoryConfiguration = configurationMap.get(repoConfigId);
        return this.listFeatureFiles(repositoryConfiguration);
    }

    public List<Path> listFeatureFiles(RepositoryConfiguration config) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles()");
        List<Path> featureFiles = new ArrayList<>();
        try {
            URI localRepoUri = config.getLocalRepoPath().toURI();
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles() - localRepoUri: " + localRepoUri);

            featureFiles = Files.walk(Paths.get(localRepoUri))
                    .filter(file -> file.getFileName().toString().endsWith(".feature"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return featureFiles;
    }

    public List<String> listFeaturesAsStrings(RepositoryConfiguration config) {
        List<String> featureFiles = new ArrayList<>();
        try {
            URI localRepoUri = config.getLocalRepoPath().toURI();
            featureFiles = listFeatureFiles(config).stream()
                    .map(path -> localRepoUri.relativize(path.toUri()).getPath())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return featureFiles;
    }
}

