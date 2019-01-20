package com.awk.featr.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

@Configuration
@Service
public class RepositoryService {
    private File localRepoPath;

    public RepositoryService( ) {
    }

    @Value("${featr.localRepoPath}")
    public void setLocalRepoPath( String localRepoPathString ) {
        this.localRepoPath = new File( requireNonNull( localRepoPathString ) );
    }

    public File getLocalRepoPath() {
        return localRepoPath;
    }

    public void cloneRepository(String repoUri) throws RepositoryException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"cloneRepository( " + repoUri + " )");

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
        Logger.getLogger(this.getClass().getName()).log(Level.INFO,"deleteRepository()");

        try {
            FileUtils.deleteDirectory(localRepoPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RepositoryException("Error deleting repo at " + localRepoPath + ", " + e.getMessage(), e);
        }
    }
}
