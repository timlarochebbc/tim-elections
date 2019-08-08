package com.tim.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "constituencyResults")
public class ConstituencyResultsRootModel {

    @JacksonXmlElementWrapper(localName = "constituencyResult", useWrapping = false)
    private ConstituencyResultModel constituencyResult;

    public ConstituencyResultsRootModel(){
    }

    public ConstituencyResultsRootModel(ConstituencyResultModel results){
        this.constituencyResult = results;
    }

    public ConstituencyResultModel getAllConstituencyResult() {
        return constituencyResult;
    }

    public void setConstituencyResult(ConstituencyResultModel constituencyResult) {
        this.constituencyResult = constituencyResult;
    }
}
