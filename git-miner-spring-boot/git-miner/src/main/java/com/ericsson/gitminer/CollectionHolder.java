package com.ericsson.gitminer;

import com.ericsson.collections.*;
import com.ericsson.csv_to_json.CSVToGraph;
import com.ericsson.csv_to_json.CsvToJsonHolder;
import com.ericsson.repositories.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CollectionHolder {


    public boolean addToCorrectTable(String parentDirectory, String fileName, String pathToFile) {
        ArrayList<String> headerKeys = new ArrayList<>();
        headerKeys.add("title");
        headerKeys.add("attribute");
        final CsvToJsonHolder commit = new CsvToJsonHolder(new File(pathToFile), headerKeys);
        RepoRepository repoTable = SpringContextConfig.getBean(RepoRepository.class);
        repoTable.save(new RepoTableCollection(parentDirectory));
        if (fileName.equals(Repositories.STATS_CONTRIBUTORS.getRepo())) {
            return addToAuthorTable(parentDirectory, commit);
        } else if (fileName.equals(Repositories.INFO_COMMITS.getRepo())) {
            return addToCommitTable(parentDirectory, commit);
        } else if (fileName.equals(Repositories.CHANGE_SET.getRepo())) {
            final CSVToGraph graphCommit = new CSVToGraph(new File(pathToFile));
            return addToChangeSet(parentDirectory, graphCommit);
        } else if (fileName.equals(Repositories.MODIFICATIONS_INFO.getRepo())) {
            return addToHashTable(commit, headerKeys);
        } else if (fileName.equals(Repositories.CODE_CHURN_AND_HUNKS_COUNT.getRepo())) {
            return addToCodeChurnAndHunksCount(parentDirectory, commit);
        } else if (fileName.equals(Repositories.CONTRIBUTORS_COUNT.getRepo())) {
            return addToContributorsCount(parentDirectory, commit);
        } else if (fileName.equals(Repositories.STATS_REPOSITORY.getRepo())) {
            final CSVToGraph graphCommit = new CSVToGraph(new File(pathToFile));
            return addToStatsRepository(parentDirectory, graphCommit);
        }

        return false;
    }

    private boolean addToCommitTable(String parentDirectory, CsvToJsonHolder commit) {
        try {
            System.out.println("ADDING TO COMMIT TABLE");
            CommitTableRepository commitTable = SpringContextConfig.getBean(CommitTableRepository.class);
            final CommitTableCollection commitTableRow = new CommitTableCollection(parentDirectory, commit.getHeaderAndValues(commit.getCsvToJson()));
            commitTable.save(commitTableRow);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    private boolean addToAuthorTable(String parentDirectory, CsvToJsonHolder commit) {
        try {
            AuthorTableRepository authorTable = SpringContextConfig.getBean(AuthorTableRepository.class);
            final AuthorTableCollection authorTableRow = new AuthorTableCollection(parentDirectory, commit.getHeaderAndValues(commit.getCsvToJson()));
            authorTable.save(authorTableRow);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    private boolean addToChangeSet(String parentDirectory, CSVToGraph commit) {
        try {
            GraphRepository graph = SpringContextConfig.getBean(GraphRepository.class);
            final GraphCollection graphRow = new GraphCollection(parentDirectory, commit.generateGraphJSON());
            graph.save(graphRow);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean addToHashTable(CsvToJsonHolder commit, List<String> headerKeys) {
        try {
            HashTableRepository hashTable = SpringContextConfig.getBean(HashTableRepository.class);
            HashSet<String> seen = new HashSet<>();
            final List<Map<String, String>> json = commit.getCsvToJson();
            for (Map<String, String> row : json) {
                final String hash = row.get("hash");
                final ArrayList<Map<String, String>> hashRow = new ArrayList<>();
                if (!seen.contains(hash)) {
                    for (Map<String, String> read : json) {
                        if (hash.equals(read.get("hash"))) {
                            hashRow.add(read);
                        }
                    }
                    final HashTableCollection hashTableRow = new HashTableCollection(hash, commit.getHeaderAndValues(headerKeys, hashRow, new ArrayList<>(commit.getRow().keySet())));
                    hashTable.save(hashTableRow);
                }
                seen.add(hash);
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }


    private boolean addToCodeChurnAndHunksCount(String parentDirectory, CsvToJsonHolder commit) {
        try {
            CodeChurnksHunksCountRepository codeChurnTable = SpringContextConfig.getBean(CodeChurnksHunksCountRepository.class);
            final CodeChurnAndHunksCountCollection codeChurnRow = new CodeChurnAndHunksCountCollection(parentDirectory, commit.getHeaderAndValues(commit.getCsvToJson()));
            codeChurnTable.save(codeChurnRow);
            return true;
        } catch (IOException e) {
            return false;
        }

    }


    private boolean addToContributorsCount(String parentDirectory, CsvToJsonHolder commit) {
        try {
            ContributorsCountRepository contributorsCount = SpringContextConfig.getBean(ContributorsCountRepository.class);
            final ContributorsCountCollection contributorsCountRow = new ContributorsCountCollection(parentDirectory, commit.getHeaderAndValues(commit.getCsvToJson()));
            contributorsCount.save(contributorsCountRow);
            return true;
        } catch (IOException e) {
            return false;
        }

    }


    private boolean addToStatsRepository(String parentDirectory, CSVToGraph commit) {
        try {
            StatsRRepository graph = SpringContextConfig.getBean(StatsRRepository.class);
            final StatsRepositoryGraphCollection graphRow = new StatsRepositoryGraphCollection(parentDirectory, commit.generateGraphJSON());
            graph.save(graphRow);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
