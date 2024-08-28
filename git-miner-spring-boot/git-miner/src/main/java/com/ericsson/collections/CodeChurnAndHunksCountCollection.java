package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("CodeChurnAndHunksCountCollection")
public class CodeChurnAndHunksCountCollection {
    @Id
    private String repoName;
    private Map<String, List<Map<String, String>>> codeChurnsAndHunksCountHeadersAndValues;

    public CodeChurnAndHunksCountCollection(String repoName, Map<String, List<Map<String, String>>> codeChurnsAndHunksCountHeadersAndValues) {
        this.repoName = repoName;
        this.codeChurnsAndHunksCountHeadersAndValues = codeChurnsAndHunksCountHeadersAndValues;
    }

    public String getRepoName() {
        return repoName;
    }

    public Map<String, List<Map<String, String>>> getCodeChurnsAndHunksCountHeadersAndValues() {
        return codeChurnsAndHunksCountHeadersAndValues;
    }

    public String toString() {
        return getRepoName() + getCodeChurnsAndHunksCountHeadersAndValues().toString();
    }
}
