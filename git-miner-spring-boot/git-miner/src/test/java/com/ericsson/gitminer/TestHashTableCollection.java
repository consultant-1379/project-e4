package com.ericsson.gitminer;

import com.ericsson.collections.HashTableCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHashTableCollection {

    private final String TEST_HASH = "test hash";
    private final String EMPTY_HASH = "";
    private final Map<String, List<Map<String, String>>> EMPTY_HEADER_AND_VALUES = new HashMap<>();


    @Test
    public void verifyThatHashTableCanBeCreated() {
        HashTableCollection empty = new HashTableCollection(EMPTY_HASH, EMPTY_HEADER_AND_VALUES);
        assertEquals(empty.toString(), EMPTY_HEADER_AND_VALUES.toString());
    }

    @Test
    public void verifyThatCanGetRepoName() {
        HashTableCollection repoName = new HashTableCollection(TEST_HASH, EMPTY_HEADER_AND_VALUES);
        assertEquals(repoName.getHash(), TEST_HASH);
    }

    @Test
    public void verifyThatCanGetHeadersAndValues() {
        HashMap<String, String> dummyValue = new HashMap<>();
        dummyValue.put("key", "value");
        HashMap<String, List<Map<String, String>>> parentMap = new HashMap<>();
        ArrayList<Map<String, String>> withValues = new ArrayList<>();
        withValues.add(dummyValue);
        parentMap.put("parent", withValues);
        HashTableCollection filled = new HashTableCollection(TEST_HASH, parentMap);
        assertEquals(filled.getHashTableHeaderAndValues().toString(), parentMap.toString());
    }
}
