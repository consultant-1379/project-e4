package com.ericsson.gitminer;

import com.ericsson.collections.RepoTableCollection;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRepoTableCollection {

    private final String TEST_REPO_NAME = "test hash";
    private final String EMPTY_REPO_NAME = "";

    @Test
    public void verifyThatHashTableCanBeCreated() {
        RepoTableCollection empty = new RepoTableCollection(EMPTY_REPO_NAME);
        assertEquals(empty.toString(), EMPTY_REPO_NAME);
    }

    @Test
    public void verifyThatCanGetRepoName() {
        RepoTableCollection repoName = new RepoTableCollection(TEST_REPO_NAME);
        assertEquals(repoName.getRepoName(), TEST_REPO_NAME);
    }

}
