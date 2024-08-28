package com.ericsson.gitminer;

import com.ericsson.collections.*;
import com.ericsson.endpoints.RepoInfo;
import com.ericsson.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)


public class TestRepoInfo {

    @InjectMocks
    RepoInfo repoInfo;

    @Mock
    CommitTableRepository commitTable;
    @Mock
    AuthorTableRepository authorTable;
    @Mock
    HashTableRepository hashTable;
    @Mock
    GraphRepository graph;
    @Mock
    RepoRepository repo;
    @Mock
    CodeChurnksHunksCountRepository codeChurnAndHunksCountTable;
    @Mock
    ContributorsCountRepository contributors;
    @Mock
    StatsRRepository stats;

    final Map<String, List<Map<String, String>>> FILLED_MAP = new HashMap<>();
    final List<Map<String, String>> FILLED_LIST = new ArrayList<>();

    @Before
    public void init() {
        HashMap<String, String> dummyValue = new HashMap<>();
        dummyValue.put("key", "value");
        ArrayList<Map<String, String>> withValues = new ArrayList<>();
        withValues.add(dummyValue);
        FILLED_MAP.put("parent", withValues);

        Map<String, String> graphRow = new HashMap<>();
        graphRow.put("Word", "Word1");
        graphRow.put("Value", "Value1");
        FILLED_LIST.add(graphRow);
    }

    @Test
    public void verifyThatCanGetCommitTable() {
        Map<String, List<Map<String, String>>> empty = new HashMap<>();
        Optional<CommitTableCollection> commits = Optional.of(new CommitTableCollection("repo-name", empty));
        Mockito.when(commitTable.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getCommitTable("repo-name", "test", "test-2");
        assertEquals(result.toString(), empty.toString());
    }


    @Test
    public void verifyThatCanGetCommitTableWithData() {
        Optional<CommitTableCollection> commits = Optional.of(new CommitTableCollection("repo-name", FILLED_MAP));
        Mockito.when(commitTable.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getCommitTable("repo-name", "", "");
        assertEquals(result.toString(), FILLED_MAP.toString());
    }

    @Test
    public void verifyThatCanGetAuthorTable() {
        Map<String, List<Map<String, String>>> empty = new HashMap<>();
        Optional<AuthorTableCollection> commits = Optional.of(new AuthorTableCollection("repo-name", empty));
        Mockito.when(authorTable.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getAuthorTable("repo-name", "test", "test-2");
        assertEquals(result.toString(), empty.toString());
    }

    @Test
    public void verifyThatCanGetAuthorTableWithData() {
        Optional<AuthorTableCollection> commits = Optional.of(new AuthorTableCollection("repo-name", FILLED_MAP));
        Mockito.when(authorTable.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getAuthorTable("repo-name", "", "");
        assertEquals(result.toString(), FILLED_MAP.toString());
    }



    @Test
    public void verifyThatCanGetHashTable() {
        Map<String, List<Map<String, String>>> empty = new HashMap<>();
        Optional<HashTableCollection> commits = Optional.of(new HashTableCollection("hash", empty));
        Mockito.when(hashTable.findById("hash")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getHashTable("hash");
        assertEquals(result.toString(), empty.toString());
    }

    @Test
    public void verifyThatCanGetHashTableWithData() {
        Optional<HashTableCollection> commits = Optional.of(new HashTableCollection("hash", FILLED_MAP));
        Mockito.when(hashTable.findById("hash")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getHashTable("hash");
        assertEquals(result.toString(), FILLED_MAP.toString());
    }


    @Test
    public void verifyThatCanGetGraph() {
        List<Map<String, String>> empty = new ArrayList<>();
        Optional<GraphCollection> commits = Optional.of(new GraphCollection("repo-name", empty));
        Mockito.when(graph.findById("repo-name")).thenReturn(commits);
        List<Map<String, String>> result = repoInfo.getChangeSet("repo-name", "", "");
        assertEquals(result.toString(), empty.toString());
    }


    @Test
    public void verifyThatCanGetGraphWithData() {
        Optional<GraphCollection> commits = Optional.of(new GraphCollection("repo-name", FILLED_LIST));
        Mockito.when(graph.findById("repo-name")).thenReturn(commits);
        List<Map<String, String>> result = repoInfo.getChangeSet("repo-name", "", "");
        assertEquals(result.toString(), FILLED_LIST.toString());
    }


    @Test
    public void verifyThatCanGetCodeChurnAndHunksCountTable() {
        Map<String, List<Map<String, String>>> empty = new HashMap<>();
        Optional<CodeChurnAndHunksCountCollection> commits = Optional.of(new CodeChurnAndHunksCountCollection("repo-name", empty));
        Mockito.when(codeChurnAndHunksCountTable.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getCodeChurnAndHunksCount("repo-name", "test", "test-2");
        assertEquals(result.toString(), empty.toString());
    }


    @Test
    public void verifyThatCanGetCodeChurnAndHunksCountTableWithData() {
        Optional<CodeChurnAndHunksCountCollection> commits = Optional.of(new CodeChurnAndHunksCountCollection("repo-name", FILLED_MAP));
        Mockito.when(codeChurnAndHunksCountTable.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getCodeChurnAndHunksCount("repo-name", "", "");
        assertEquals(result.toString(), FILLED_MAP.toString());
    }

    @Test
    public void verifyThatCanGetContributors() {
        Map<String, List<Map<String, String>>> empty = new HashMap<>();
        Optional<ContributorsCountCollection> commits = Optional.of(new ContributorsCountCollection("repo-name", empty));
        Mockito.when(contributors.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getContributorsCount("repo-name", "test", "test-2");
        assertEquals(result.toString(), empty.toString());
    }


    @Test
    public void verifyThatCanGetContributorsWithData() {
        Optional<ContributorsCountCollection> commits = Optional.of(new ContributorsCountCollection("repo-name", FILLED_MAP));
        Mockito.when(contributors.findById("repo-name")).thenReturn(commits);
        Map<String, List<Map<String, String>>> result = repoInfo.getContributorsCount("repo-name", "", "");
        assertEquals(result.toString(), FILLED_MAP.toString());
    }

    @Test
    public void verifyThatCanGetStats() {
        List<Map<String, String>> empty = new ArrayList<>();
        Optional<StatsRepositoryGraphCollection> commits = Optional.of(new StatsRepositoryGraphCollection("repo-name", empty));
        Mockito.when(stats.findById("repo-name")).thenReturn(commits);
        List<Map<String, String>> result = repoInfo.getStatsRepository("repo-name", "test", "test-2");
        assertEquals(result.toString(), empty.toString());
    }

    @Test
    public void verifyThatCanGetStatsWithData() {
        Optional<StatsRepositoryGraphCollection> commits = Optional.of(new StatsRepositoryGraphCollection("repo-name", FILLED_LIST));
        Mockito.when(stats.findById("repo-name")).thenReturn(commits);
        List<Map<String, String>> result = repoInfo.getStatsRepository("repo-name", "", "");
        assertEquals(result.toString(), FILLED_LIST.toString());
    }



    @Test
    public void verifyThatCanGetRepos() {
        List<RepoTableCollection> getReposList = new ArrayList<>();
        Mockito.when(repo.findAll()).thenReturn(getReposList);
        List<RepoTableCollection> returned = repoInfo.getRepos();
        assertEquals(getReposList.toString(), returned.toString());
    }
}
