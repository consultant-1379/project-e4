package com.ericsson.gitminer;

import com.ericsson.collections.CommitTableCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCommitTableCollection {

    private final String TEST_REPO_NAME = "test repo name";
    private final String EMPTY_REPO_NAME = "";
    private final Map<String, List<Map<String, String>>> EMPTY_HEADER_AND_VALUES = new HashMap<>();


    @Test
    public void verifyThatCommitTableCanBeCreated() {
        CommitTableCollection empty = new CommitTableCollection(EMPTY_REPO_NAME, EMPTY_HEADER_AND_VALUES);
        assertEquals(empty.toString(), EMPTY_HEADER_AND_VALUES.toString());
    }

    @Test
    public void verifyThatCanGetRepoName() {
        CommitTableCollection repoName = new CommitTableCollection(TEST_REPO_NAME, EMPTY_HEADER_AND_VALUES);
        assertEquals(repoName.getRepoName(), TEST_REPO_NAME);
    }

    @Test
    public void verifyThatCanGetHeadersAndValues() {
        HashMap<String, String> dummyValue = new HashMap<>();
        dummyValue.put("key", "value");
        ArrayList<Map<String, String>> listToMap = new ArrayList<>();
        listToMap.add(dummyValue);
        HashMap<String, List<Map<String, String>>> withValues = new HashMap<>();
        withValues.put("parent", listToMap);
        CommitTableCollection filled = new CommitTableCollection(TEST_REPO_NAME, withValues);
        assertEquals(filled.getCommitTableHeaderAndValues().toString(), withValues.toString());
    }
}
