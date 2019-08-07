package com.tim.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "constituencyResults")
public class ConstituencyResults {

    @JacksonXmlElementWrapper(localName = "constituencyResult", useWrapping = false)
    private ConstituencyResult[] constituencyResult;

    public ConstituencyResults(){
    }

    public ConstituencyResults(ConstituencyResult[] results){
        this.constituencyResult = results;
    }

    public ConstituencyResult[] getConstituencyResult() {
        return constituencyResult;
    }

    public void setConstituencyResult(ConstituencyResult[] constituencyResult) {
        this.constituencyResult = constituencyResult;
    }
}
