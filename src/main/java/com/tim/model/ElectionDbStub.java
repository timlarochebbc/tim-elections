package com.tim.model;

import java.util.HashMap;
import java.util.Map;

public class ElectionDbStub implements DbStub {
    private static Map<Integer, ResultModel[]> resultsById;

    static{
        resultsById = new HashMap<Integer, ResultModel[]>();
    }

    public Map<Integer, ResultModel[]> getAll() {
        return resultsById;
    }


    public static void addNewResult(int id, ResultModel[] results){
        resultsById.put(id, results);
    }
}
