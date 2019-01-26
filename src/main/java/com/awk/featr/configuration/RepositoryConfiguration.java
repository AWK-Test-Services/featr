package com.awk.featr.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import static java.util.Objects.requireNonNull;

@Configuration
public class RepositoryConfiguration {

    private static String repoUri = "git@server:/repositories/dctnry.git";


    private File localRepoPath;

//    private String remoteRepoLocation;


    @Value("${featr.localRepoPath}")
    public void setLocalRepoPath(String localRepoPathString) {
        this.localRepoPath = new File(requireNonNull(localRepoPathString));
    }

    public File getLocalRepoPath() {
        return localRepoPath;
    }

    public String getRepoLocation() {
        return repoUri;
    }
}
