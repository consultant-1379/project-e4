package com.ericsson.gitminer;

import com.ericsson.endpoints.RepoInfo;
import com.ericsson.repositories.CommitTableRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@ComponentScan(basePackageClasses = {RepoInfo.class, FillDatabaseFromCSV.class})
@EnableMongoRepositories(basePackageClasses = {CommitTableRepository.class})
public class GitMinerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitMinerApplication.class, args);
    }
}
