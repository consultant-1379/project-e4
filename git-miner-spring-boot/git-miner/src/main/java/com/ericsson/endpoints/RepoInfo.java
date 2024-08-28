package com.ericsson.endpoints;

import com.ericsson.collections.*;
import com.ericsson.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RepoInfo {

    @Autowired
    CommitTableRepository commitTable;
    @Autowired
    AuthorTableRepository authorTable;
    @Autowired
    HashTableRepository hashTable;
    @Autowired
    GraphRepository graph;
    @Autowired
    RepoRepository repo;
    @Autowired
    CodeChurnksHunksCountRepository codeChurnAndHunksCountTable;
    @Autowired
    ContributorsCountRepository contributors;
    @Autowired
    StatsRRepository stats;

    @Value("${flask.uri}")
    String externalApi;
    @Value("${flask.uri.endpoint}")
    String endpoint;
    @Value("${repos.directory}")
    String repoDirectory;

    final RestTemplate contact = new RestTemplate();

    @GetMapping(value = "/getRepos", produces = {"application/json"})
    public List<RepoTableCollection> getRepos() {
        return repo.findAll();
    }

    @GetMapping(value = "/generateRepoData", produces = {"application/json"})
    public String generateRepoData(@RequestParam String repository, @RequestParam String from, @RequestParam String to) {
        final String PASS_DATE_INFO = String.format("?repository=%s&date_from=%s&date_to=%s", repository, from, to);
        return contact.getForObject(externalApi + endpoint + PASS_DATE_INFO, String.class);
    }

    @GetMapping(value = "/getCommitTable/{repoName}", produces = {"application/json"})
    public Map<String, List<Map<String, String>>> getCommitTable(@PathVariable String repoName, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Optional<CommitTableCollection> result = commitTable.findById(repoName + from + to);

        if (result.isPresent()) {
            return result.get().getCommitTableHeaderAndValues();
        }

        return new HashMap<>();
    }

    @GetMapping(value = "/getAuthorTable/{repoName}", produces = {"application/json"})
    public Map<String, List<Map<String, String>>> getAuthorTable(@PathVariable String repoName, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Optional<AuthorTableCollection> result = authorTable.findById(repoName + from + to);

        if (result.isPresent()) {
            return result.get().getAuthorTableHeaderAndValues();
        }

        return new HashMap<>();
    }


    @GetMapping(value = "/getHashTable/{commitHash}", produces = {"application/json"})
    public Map<String, List<Map<String, String>>> getHashTable(@PathVariable String commitHash) {
        Optional<HashTableCollection> result = hashTable.findById(commitHash);

        if (result.isPresent()) {
            return result.get().getHashTableHeaderAndValues();
        }

        return new HashMap<>();
    }

    @GetMapping(value = "/getChangeSet/{repoName}", produces = {"application/json"})
    public List<Map<String, String>> getChangeSet(@PathVariable String repoName, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Optional<GraphCollection> result = graph.findById(repoName + from + to);

        if (result.isPresent()) {
            return result.get().getGraphHeaderAndValues();
        }

        return new ArrayList<>();
    }

    @GetMapping(value = "/getStatsRepository/{repoName}", produces = {"application/json"})
    public List<Map<String, String>> getStatsRepository(@PathVariable String repoName, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Optional<StatsRepositoryGraphCollection> result = stats.findById(repoName + from + to);

        if (result.isPresent()) {
            return result.get().getGraphHeaderAndValues();
        }

        return new ArrayList<>();
    }


    @GetMapping(value = "/getCodeChurnAndHunksCount/{repoName}", produces = {"application/json"})
    public Map<String, List<Map<String, String>>> getCodeChurnAndHunksCount(@PathVariable String repoName, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Optional<CodeChurnAndHunksCountCollection> result = codeChurnAndHunksCountTable.findById(repoName + from + to);

        if (result.isPresent()) {
            return result.get().getCodeChurnsAndHunksCountHeadersAndValues();
        }

        return new HashMap<>();
    }


    @GetMapping(value = "/getContributorsCount/{repoName}", produces = {"application/json"})
    public Map<String, List<Map<String, String>>> getContributorsCount(@PathVariable String repoName, @RequestParam(required = false) String from, @RequestParam(required = false) String to) {
        Optional<ContributorsCountCollection> result = contributors.findById(repoName + from + to);

        if (result.isPresent()) {
            return result.get().getContributorsCountHeadersAndValues();
        }

        return new HashMap<>();
    }


}