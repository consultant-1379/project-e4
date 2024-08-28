package com.ericsson.gitminer;

import com.ericsson.repositories.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {GitMinerApplication.class, AuthorTableRepository.class, CodeChurnksHunksCountRepository.class, CommitTableRepository.class, ContributorsCountRepository.class, GraphRepository.class, HashTableRepository.class, RepoRepository.class, StatsRRepository.class, SpringContextConfig.class})
public class TestCollectionHolder {

    private final String ABSOLUTE_PATH = new File("").getAbsolutePath();
    private final String TEST_FILE_DIRECTORY = ABSOLUTE_PATH + "/src/main/resources/test_files";
    private final String TEST_FILE_DIRECTORY_EMPTY = "";

    @Test
    public void verifyThatAddedToStatsContributors() {
        String fileName = "stats_contributors.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }

    @Test
    public void verifyThatAddedToInfoCommits() {
        String fileName = "info_commits.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }

    @Test
    public void verifyThatAddedToChangeSet() {
        String fileName = "change_set.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }

    @Test
    public void verifyThatAddedToModificationsInfo() {
        String fileName = "modifications_info.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }

    @Test
    public void verifyThatAddedToCodeChurnAndHunksCount() {
        String fileName = "code_churn.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }

    @Test
    public void verifyThatAddedToContributorsCount() {
        String fileName = "contributors_count.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }

    @Test
    public void verifyThatAddedToStatsRepository() {
        String fileName = "stats_repository.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY + "/" + fileName);
        assertTrue(result);
    }


    @Test
    public void verifyThatFailedToStatsContributors() {
        String fileName = "stats_contributors.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }

    @Test
    public void verifyThatFailedToInfoCommits() {
        String fileName = "info_commits.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }

    @Test
    public void verifyThatFailedToChangeSet() {
        String fileName = "change_set.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }

    @Test
    public void verifyThatFailedToModificationsInfo() {
        String fileName = "modifications_info.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }

    @Test
    public void verifyThatFailedToCodeChurnAndHunksCount() {
        String fileName = "code_churn.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }

    @Test
    public void verifyThatFailedToContributorsCount() {
        String fileName = "contributors_count.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }

    @Test
    public void verifyThatFailedToStatsRepository() {
        String fileName = "stats_repository.csv";
        CollectionHolder holder = new CollectionHolder();
        boolean result = holder.addToCorrectTable("test_files", fileName, TEST_FILE_DIRECTORY_EMPTY + "/" + fileName);
        assertFalse(result);
    }
}
