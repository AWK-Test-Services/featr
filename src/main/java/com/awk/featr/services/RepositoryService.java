package com.awk.featr.services;

import com.awk.featr.configuration.RepositoryConfiguration;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

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

@Service
public class RepositoryService {

//    @Autowired
//    public RepositoryService() {}

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

    public List<Path> listFeatureFiles(RepositoryConfiguration config, Optional<Long> maxOptionalSize ) {
        Long maxSize = maxOptionalSize.orElse(10L);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "listFeatureFiles( " + maxSize + " )");
        return listFeatureFiles(config).stream().limit(maxSize).collect(Collectors.toList());
    }

    private List<Path> listFeatureFiles(RepositoryConfiguration config) {
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

