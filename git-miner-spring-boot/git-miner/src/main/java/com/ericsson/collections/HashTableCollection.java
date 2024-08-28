package com.ericsson.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document("HashTable")
public class HashTableCollection {
    @Id
    private String hash;
    private Map<String, List<Map<String, String>>> hashTableHeaderAndValues;

    public HashTableCollection(String hash, Map<String, List<Map<String, String>>> hashTableHeaderAndValues) {
        this.hash = hash;
        this.hashTableHeaderAndValues = hashTableHeaderAndValues;
    }

    public String getHash() {
        return hash;
    }

    public Map<String, List<Map<String, String>>> getHashTableHeaderAndValues() {
        return hashTableHeaderAndValues;
    }

    public String toString() {
        return getHash() + getHashTableHeaderAndValues().toString();
    }

}
