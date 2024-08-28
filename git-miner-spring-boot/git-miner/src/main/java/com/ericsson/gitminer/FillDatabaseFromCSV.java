package com.ericsson.gitminer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.File;
import java.time.Duration;

@Configuration
public class FillDatabaseFromCSV {

    @Value("${repos.directory}")
    private String directory;

    @Value("${repos.start}")
    private long start;
    @Value("${repos.end}")
    private long end;

    private boolean watchingDirectory;
    private boolean beanDestroyed;

    @Bean
    public FileSystemWatcher watchForRepoAddition() {
        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(start), Duration.ofMillis(end));
        fileSystemWatcher.addSourceDirectory(new File(directory));
        fileSystemWatcher.addListener(new RepoFilesAddedListener());
        fileSystemWatcher.start();
        watchingDirectory = true;
        return fileSystemWatcher;
    }


    @PreDestroy
    public void onDestroy() {
        watchForRepoAddition().stop();
        beanDestroyed = true;
    }

    public boolean getWatchingDirectory() {
        return watchingDirectory;
    }

    public boolean getBeanDestroyed() {
        return beanDestroyed;
    }
}
