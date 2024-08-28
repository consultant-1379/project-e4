package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Repos")
public class RepoTableCollection {
    @Id
    private String repoName;

    public RepoTableCollection(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoName() {
        return repoName;
    }

    public String toString() {
        return getRepoName();
    }
}
