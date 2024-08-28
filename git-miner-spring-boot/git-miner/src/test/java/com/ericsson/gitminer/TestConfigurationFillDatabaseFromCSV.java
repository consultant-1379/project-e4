package com.ericsson.gitminer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfigurationFillDatabaseFromCSV {
    @Bean
    public FillDatabaseFromCSV testFill() {
        return new FillDatabaseFromCSV();
    }
}
