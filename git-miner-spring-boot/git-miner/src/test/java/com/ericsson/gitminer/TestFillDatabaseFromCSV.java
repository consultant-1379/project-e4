package com.ericsson.gitminer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfigurationFillDatabaseFromCSV.class)
public class TestFillDatabaseFromCSV {


    @Autowired
    private FillDatabaseFromCSV testFill;

    @Test
    @DirtiesContext
    public void verifyThatCanWatchDirectory() {
        testFill.watchForRepoAddition();
        assertTrue(testFill.getWatchingDirectory());
    }

    @Test
    @DirtiesContext
    public void verifyThatBeanIsDestroyed() {
        testFill.onDestroy();
        assertTrue(testFill.getBeanDestroyed());
    }
}
