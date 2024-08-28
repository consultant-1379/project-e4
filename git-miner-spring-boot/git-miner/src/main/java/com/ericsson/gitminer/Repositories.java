package com.ericsson.gitminer;

public enum Repositories {
    STATS_CONTRIBUTORS("stats_contributors.csv"),
    INFO_COMMITS("info_commits.csv"),
    CHANGE_SET("change_set.csv"),
    STATS_REPOSITORY("stats_repository.csv"),
    CONTRIBUTORS_COUNT("contributors_count.csv"),
    CODE_CHURN_AND_HUNKS_COUNT("code_churn.csv"),
    MODIFICATIONS_INFO("modifications_info.csv");

    private final String repo;

    Repositories(String repo) {
        this.repo = repo;
    }

    public String getRepo() {
        return repo;
    }
}
