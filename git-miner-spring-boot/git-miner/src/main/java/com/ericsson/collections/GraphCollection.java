package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("Graph")
public class GraphCollection {
    @Id
    private String repoName;
    private List<Map<String, String>> graphHeaderAndValues;

    public GraphCollection(String repoName, List<Map<String, String>> graphHeaderAndValues) {
        this.repoName = repoName;
        this.graphHeaderAndValues = graphHeaderAndValues;
    }

    public String getRepoName() {
        return repoName;
    }

    public List<Map<String, String>> getGraphHeaderAndValues() {
        return graphHeaderAndValues;
    }

    public String toString() {
        return getRepoName() + getGraphHeaderAndValues().toString();
    }
}
