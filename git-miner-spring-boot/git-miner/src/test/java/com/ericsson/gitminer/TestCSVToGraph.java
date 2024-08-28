package com.ericsson.gitminer;

import com.ericsson.csv_to_json.CSVToGraph;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class TestCSVToGraph {

    final String EMPTY = "";
    final String ABSOLUTE = new File("").getAbsolutePath();

    @Test
    public void verifyThatCanCreateCSVToGraph() {
        CSVToGraph empty = new CSVToGraph(new File(""));
        assertEquals(empty.toString(), EMPTY);
    }

    @Test
    public void verifyThatCanGenerateGraphJSON() throws IOException {
        CSVToGraph testGraph = new CSVToGraph(new File("./src/main/resources/test_files/test-repo-table.csv"));
        assertEquals(testGraph.generateGraphJSON().toString(), "[{Word=Repo, Value=Repo1}, {Word=Table, Value=Table1}]");
    }
}
