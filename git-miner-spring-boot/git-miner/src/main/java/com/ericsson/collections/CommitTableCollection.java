package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("CommitTable")
public class CommitTableCollection {

    @Id
    private String repoName;
    private Map<String, List<Map<String, String>>> commitTableHeaderAndValues;


    public CommitTableCollection(String repoName, Map<String, List<Map<String, String>>> commitTableHeaderAndValues) {
        this.repoName = repoName;
        this.commitTableHeaderAndValues = commitTableHeaderAndValues;
    }

    public String getRepoName() {
        return repoName;
    }

    public Map<String, List<Map<String, String>>> getCommitTableHeaderAndValues() {
        return commitTableHeaderAndValues;
    }

    public String toString() {
        return getRepoName() + getCommitTableHeaderAndValues().toString();
    }
}
