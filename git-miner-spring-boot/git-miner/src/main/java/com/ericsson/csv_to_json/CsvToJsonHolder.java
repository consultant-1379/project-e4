package com.ericsson.csv_to_json;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class CsvToJsonHolder {
    private final File csv;
    private Map<String, String> row;
    private final List<String> headerKeys;

    public CsvToJsonHolder(File csv, List<String> headerKeys) {
        this.csv = csv;
        this.headerKeys = headerKeys;
    }


    //Attaches columns (headers) and data (values) into one object
    public Map<String, List<Map<String, String>>> getHeaderAndValues(List<Map<String, String>> jsonData) throws IOException {
        HashMap<String, List<Map<String, String>>> headerAndValues = new HashMap<>();
        headerAndValues.put("data", jsonData);
        headerAndValues.put("columns", getHeaderFromJsonKeys(headerKeys, new ArrayList<>(row.keySet())));
        return headerAndValues;
    }

    public Map<String, List<Map<String, String>>> getHeaderAndValues(List<String> headerKeys, List<Map<String, String>> jsonData, ArrayList<String> keySetRow) throws IOException {
        HashMap<String, List<Map<String, String>>> headerAndValues = new HashMap<>();
        headerAndValues.put("data", jsonData);
        headerAndValues.put("columns", getHeaderFromJsonKeys(headerKeys, keySetRow));
        return headerAndValues;
    }

    //Build a collection of map (json) from csv file, the keys to the maps will be based on the headers of the CSV file
    public List<Map<String, String>> getCsvToJson() throws IOException {
        List<Map<String, String>> repoHistory;
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(Map.class).withHeader();
        MappingIterator<Map<String, String>> mappingIterator = csvMapper.readerFor(Map.class).with(csvSchema).readValues(csv);
        repoHistory = mappingIterator.readAll();
        row = repoHistory.get(0);
        return repoHistory;
    }

    public Map<String, String> getRow() {
        return row;
    }

    //Build a collection of jsons based on keys value
    //It is build in a specific way which is ALL keys have a replica of the same value (This is to adhere to EUI tables)
    private List<Map<String, String>> getHeaderFromJsonKeys(List<String> keys, List<String> values) {
        return produceJsonFromKeyValue(keys, values);
    }

    private List<Map<String, String>> produceJsonFromKeyValue(List<String> keys, List<String> values) {
        ArrayList<Map<String, String>> jsonList = new ArrayList<>();
        for (String value : values) {
            HashMap<String, String> json = new HashMap<>();
            for (String key : keys) {
                json.put(key, value);
            }
            jsonList.add(json);
        }
        return jsonList;
    }

}
