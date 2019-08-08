package com.tim.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ConstituencyResultModel {

    @JacksonXmlProperty(localName = "seqNo", isAttribute = true)
    private int seqNo;

    @JacksonXmlProperty(localName = "consituencyId")
    private int consituencyId;

    @JacksonXmlProperty(localName = "constituencyName")
    private String constituencyName;

    @JacksonXmlElementWrapper(localName = "results", useWrapping = true)
    private ResultModel[] results;

    public ConstituencyResultModel(){}

    public ConstituencyResultModel(int consituencyId, String constituencyName, ResultModel[] results){
        this.consituencyId = consituencyId;
        this.constituencyName = constituencyName;
        this.results = results;
    }

    public int getConsituencyId() {
        return consituencyId;
    }

    public void setConsituencyId(int consituencyId) {
        this.consituencyId = consituencyId;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public ResultModel[] getResults() {
        return results;
    }

    public void setResults(ResultModel[] results) {
        this.results = results;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }
}
