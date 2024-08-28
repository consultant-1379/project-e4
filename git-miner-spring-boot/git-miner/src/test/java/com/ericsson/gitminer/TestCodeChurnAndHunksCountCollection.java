package com.ericsson.gitminer;

import com.ericsson.collections.CodeChurnAndHunksCountCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCodeChurnAndHunksCountCollection {

    private final String TEST_REPO = "test hash";
    private final String EMPTY_REPO = "";
    private final Map<String, List<Map<String, String>>> EMPTY_HEADER_AND_VALUES = new HashMap<>();


    @Test
    public void verifyThatCodeChurnAndHunksCountCollection() {
        CodeChurnAndHunksCountCollection empty = new CodeChurnAndHunksCountCollection(EMPTY_REPO, EMPTY_HEADER_AND_VALUES);
        assertEquals(empty.toString(), EMPTY_HEADER_AND_VALUES.toString());
    }

    @Test
    public void verifyThatCanGetRepoName() {
        CodeChurnAndHunksCountCollection repoName = new CodeChurnAndHunksCountCollection(TEST_REPO, EMPTY_HEADER_AND_VALUES);
        assertEquals(repoName.getRepoName(), TEST_REPO);
    }

    @Test
    public void verifyThatCanGetHeadersAndValues() {
        HashMap<String, String> dummyValue = new HashMap<>();
        dummyValue.put("key", "value");
        HashMap<String, List<Map<String, String>>> parentMap = new HashMap<>();
        ArrayList<Map<String, String>> withValues = new ArrayList<>();
        withValues.add(dummyValue);
        parentMap.put("parent", withValues);
        CodeChurnAndHunksCountCollection filled = new CodeChurnAndHunksCountCollection(TEST_REPO, parentMap);
        assertEquals(filled.getCodeChurnsAndHunksCountHeadersAndValues().toString(), parentMap.toString());
    }
}
