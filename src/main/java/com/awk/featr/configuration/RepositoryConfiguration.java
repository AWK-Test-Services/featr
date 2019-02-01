package com.awk.featr.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import static java.util.Objects.requireNonNull;

public class RepositoryConfiguration {

    private String id;

    private File local;

    private String remote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getLocalRepoPath() {
        return local;
    }

    public void setLocal(String localRepoPathString) {
        this.local = new File(requireNonNull(localRepoPathString));
    }

    public String getRepoLocation() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }
}
