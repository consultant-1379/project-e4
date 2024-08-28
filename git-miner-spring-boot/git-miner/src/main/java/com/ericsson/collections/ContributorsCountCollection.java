package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("ContributorsCount")
public class ContributorsCountCollection {
    @Id
    private String repoName;
    private Map<String, List<Map<String, String>>> contributorsCountHeadersAndValues;

    public ContributorsCountCollection(String repoName, Map<String, List<Map<String, String>>> contributorsCountHeadersAndValues) {
        this.repoName = repoName;
        this.contributorsCountHeadersAndValues = contributorsCountHeadersAndValues;
    }

    public String getRepoName() {
        return repoName;
    }

    public Map<String, List<Map<String, String>>> getContributorsCountHeadersAndValues() {
        return contributorsCountHeadersAndValues;
    }

    public String toString() {
        return getRepoName() + getContributorsCountHeadersAndValues().toString();
    }
}
