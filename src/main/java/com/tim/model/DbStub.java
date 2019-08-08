package com.tim.model;

import java.util.Map;

public interface DbStub {

    //Returns total amount of voters.
    public Map<Integer, ResultModel[]> getAll();
}
