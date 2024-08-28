package com.ericsson.gitminer;

import com.ericsson.csv_to_json.CsvToJsonHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCsvToJsonHolder {

    CsvToJsonHolder toJson;

    @Before
    public void init() {
        File f = new File("./src/main/resources/test_files/test.csv");
        System.out.println(new File("").getAbsolutePath());
        ArrayList<String> headerKeys = new ArrayList<>();
        headerKeys.add("test1");
        headerKeys.add("test2");
        toJson = new CsvToJsonHolder(f, headerKeys);
    }

    @Test
    public void verifyThatFileIsRead() throws IOException {
        assertEquals(toJson.getHeaderAndValues(toJson.getCsvToJson()).toString(), "{data=[{Test1=Row1, Test2=Row2, Test3=Row3}], columns=[{test2=Test1, test1=Test1}, {test2=Test2, test1=Test2}, {test2=Test3, test1=Test3}]}");
    }

    @Test
    public void verifyThatYouCanGetCsvToJson() throws IOException {
        assertEquals(toJson.getCsvToJson().toString(), "[{Test1=Row1, Test2=Row2, Test3=Row3}]");
    }
}
