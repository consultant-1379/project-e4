package com.ericsson.gitminer;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestRepositories {

    @Test
    public void verifyThatCanGetStatsContributors() {
        assertEquals(Repositories.STATS_CONTRIBUTORS.getRepo(), "stats_contributors.csv");
    }

    @Test
    public void verifyThatCanGetInfoCommits() {
        assertEquals(Repositories.INFO_COMMITS.getRepo(), "info_commits.csv");
    }


    @Test
    public void verifyThatCanGetChangeSet() {
        assertEquals(Repositories.CHANGE_SET.getRepo(), "change_set.csv");
    }

    @Test
    public void verifyThatCanGetStatsRepository() {
        assertEquals(Repositories.STATS_REPOSITORY.getRepo(), "stats_repository.csv");
    }


    @Test
    public void verifyThatCanGetContributorsCount() {
        assertEquals(Repositories.CONTRIBUTORS_COUNT.getRepo(), "contributors_count.csv");
    }


    @Test
    public void verifyThatCanGetCodeChurnAndHunksCounts() {
        assertEquals(Repositories.CODE_CHURN_AND_HUNKS_COUNT.getRepo(), "code_churn.csv");
    }

    @Test
    public void verifyThatCanGetModificationsInfo() {
        assertEquals(Repositories.MODIFICATIONS_INFO.getRepo(), "modifications_info.csv");
    }
}