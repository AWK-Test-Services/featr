package com.awk.featr.services;

import com.awk.featr.model.Feature;
import com.awk.featr.model.builder.FeatureBuilder;
import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Configuration
@Service
public class RepositoryService {
    private File localRepoPath;

    public RepositoryService() {
    }

    @Value("${featr.localRepoPath}")
    public void setLocalRepoPath(String localRepoPathString) {
        this.localRepoPath = new File(requireNonNull(localRepoPathString));
    }

    public File getLocalRepoPath() {
        return localRepoPath;
    }

    public void cloneRepository(String repoUri) throws RepositoryException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "cloneRepository( " + repoUri + " )");

        try {
            Git.cloneRepository()
                    .setURI(repoUri)
                    .setDirectory(localRepoPath)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            throw new RepositoryException("Error cloning repo " + repoUri + " to " + localRepoPath + ", " + e.getMessage(), e);
        }
    }

    public void deleteRepository() throws RepositoryException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "deleteRepository()");

        try {
            FileUtils.deleteDirectory(localRepoPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RepositoryException("Error deleting repo at " + localRepoPath + ", " + e.getMessage(), e);
        }
    }

    public List<Path> listFeatureFiles(Optional<Long> maxOptionalSize ) {
        Long maxSize = maxOptionalSize.orElse(10L);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles( " + maxSize + " )");
        return listFeatureFiles().stream().limit(maxSize).collect(Collectors.toList());
    }

    private List<Path> listFeatureFiles() {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles()");
        List<Path> featureFiles = new ArrayList<>();
        try {
            URI localRepoUri = localRepoPath.toURI();
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles() - localRepoUri: " + localRepoUri);

            featureFiles = Files.walk(Paths.get(localRepoUri))
                    .filter(file -> file.getFileName().toString().endsWith(".feature"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return featureFiles;
    }

    public List<String> listFeaturesAsStrings() {
        List<String> featureFiles = new ArrayList<>();
        try {
            URI localRepoUri = localRepoPath.toURI();
            featureFiles = listFeatureFiles().stream()
                    .map(path -> localRepoUri.relativize(path.toUri()).getPath())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return featureFiles;
    }
}

