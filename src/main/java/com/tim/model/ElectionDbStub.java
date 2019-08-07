package com.tim.model;

import java.util.HashMap;
import java.util.Map;

public class ElectionDbStub implements DbStub {
    private static Map<Integer, Result[]> resultsById;

    static{
        resultsById = new HashMap<Integer, Result[]>();
    }

    public Map<Integer, Result[]> getAll() {
        return resultsById;
    }


    public static void addNewResult(int id, Result[] results){
        resultsById.put(id, results);
    }
}
