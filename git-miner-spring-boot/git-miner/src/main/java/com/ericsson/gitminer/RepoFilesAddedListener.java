package com.ericsson.gitminer;
import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;

import java.io.File;
import java.util.*;

public class RepoFilesAddedListener implements FileChangeListener {

    private CollectionHolder collections;

    public RepoFilesAddedListener() {
        collections = new CollectionHolder();
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for (ChangedFiles changed : changeSet) {
            addFilesFromFileSet(changed);
        }
    }

    private void addFilesFromFileSet(ChangedFiles changed) {
        for (ChangedFile changedListener : changed) {
            if (checkForAddedFile(changedListener) && isNotDirectory(changedListener.getFile())) {
                sendToCorrectCollection(changedListener.getFile());
            }
        }
    }

    private boolean sendToCorrectCollection(File current) {
        final String pathToFile = current.getAbsolutePath();
        final String fileName = current.getName();
        final String parentDirectory = current.getParentFile().getName();
        System.out.println(pathToFile + " " + fileName + " " + parentDirectory);
        return collections.addToCorrectTable(parentDirectory, fileName, pathToFile);
    }

    private boolean isNotDirectory(File toCheck) {
        return !toCheck.isDirectory();
    }

    private boolean checkForAddedFile(ChangedFile toCheck) {
        return checkForChangedFileType(toCheck.getType(), ChangedFile.Type.ADD);
    }

    private boolean checkForChangedFileType(ChangedFile.Type fileType, ChangedFile.Type changedFileType) {
        return fileType.equals(changedFileType);
    }


}