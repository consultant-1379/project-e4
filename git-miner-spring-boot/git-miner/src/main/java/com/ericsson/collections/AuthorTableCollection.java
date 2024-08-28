package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("AuthorTable")
public class AuthorTableCollection {
    @Id
    private String repoName;
    private Map<String, List<Map<String, String>>> authorTableHeaderAndValues;

    public AuthorTableCollection(String repoName, Map<String, List<Map<String, String>>> authorTableHeaderAndValues) {
        this.repoName = repoName;
        this.authorTableHeaderAndValues = authorTableHeaderAndValues;
    }

    public String getRepoName() {
        return repoName;
    }
    
    public Map<String, List<Map<String, String>>> getAuthorTableHeaderAndValues() {
        return authorTableHeaderAndValues;
    }

    public String toString() {
        return getRepoName() + getAuthorTableHeaderAndValues().toString();
    }
}
