package com.cnwy.views.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerResult {
    public String traceID;
    public List<HashMap<String,String>> results = new ArrayList<>();

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }

    public List<HashMap<String, String>> getResults() {
        return results;
    }

    public void setResults(List<HashMap<String, String>> results) {
        this.results = results;
    }
}
