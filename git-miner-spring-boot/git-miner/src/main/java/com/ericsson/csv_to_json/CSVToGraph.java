package com.ericsson.csv_to_json;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CSVToGraph {

    private File graphFile;


    public CSVToGraph(File graphFile) {
        this.graphFile = graphFile;
    }

    public List<Map<String, String>> generateGraphJSON() throws IOException {
        List<Map<String, String>> dirtyGraph = getCsvToJson();
        ArrayList<Map<String, String>> cleanGraph = new ArrayList<>();
        for (Map<String, String> value : dirtyGraph) {
            for (Map.Entry<String, String> entries : value.entrySet()) {
                final String word = entries.getKey();
                final String wordValue = entries.getValue();
                cleanGraph.add(getGraphColumn(word, wordValue));
            }
        }

        return cleanGraph;
    }

    private List<Map<String, String>> getCsvToJson() throws IOException {
        List<Map<String, String>> repoHistory;
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(Map.class).withHeader();
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class).with(csvSchema).readValues(graphFile);
        repoHistory = mappingIterator.readAll();
        return repoHistory;
    }

    private Map<String, String> getGraphColumn(String wordValue, String value) {
        HashMap<String, String> graphColumn = new HashMap<>();
        graphColumn.put("Word", wordValue);
        graphColumn.put("Value", value);
        return graphColumn;
    }

    public String toString() {
        return graphFile.toString();
    }
}
