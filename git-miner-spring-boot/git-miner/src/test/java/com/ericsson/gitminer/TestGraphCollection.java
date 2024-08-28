package com.ericsson.gitminer;

import com.ericsson.collections.GraphCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGraphCollection {

    private final String TEST_REPO_NAME = "test hash";
    private final String EMPTY_REPO_NAME = "";
    private final List<Map<String, String>> EMPTY_GRAPH_HEADER_AND_VALUES = new ArrayList<>();

    @Test
    public void verifyThatCanCreateGraphCollection() {
        GraphCollection empty = new GraphCollection(EMPTY_REPO_NAME, EMPTY_GRAPH_HEADER_AND_VALUES);
        assertEquals(empty.toString(), EMPTY_GRAPH_HEADER_AND_VALUES.toString());
    }

    @Test
    public void verifyThatCanGetRepoName() {
        GraphCollection repoName = new GraphCollection(TEST_REPO_NAME, EMPTY_GRAPH_HEADER_AND_VALUES);
        assertEquals(repoName.getRepoName(), TEST_REPO_NAME);
    }

    @Test
    public void verifyThatCanGetGraphHeaderAndValues() {
        Map<String, String> graphRow = new HashMap<>();
        graphRow.put("Word", "Word1");
        graphRow.put("Value", "Value1");
        ArrayList<Map<String, String>> graphWithHeaders = new ArrayList<>();
        graphWithHeaders.add(graphRow);
        GraphCollection repoName = new GraphCollection(EMPTY_REPO_NAME, graphWithHeaders);
        assertEquals(repoName.getGraphHeaderAndValues().toString(), graphWithHeaders.toString());
    }
}
