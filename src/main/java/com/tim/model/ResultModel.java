package com.tim.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ResultModel {

    /*
    Does not work. https://github.com/FasterXML/jackson-dataformat-xml/issues/121
    enum PARTIES {
        LAB("LAB"),
        CON("CON"),
        LD("LD"),
        PC("PC"),
        OTH("OTH"),
        GRN("GRN"),
        UKIP("UKIP");

        private String value;

        private PARTIES(String value){
            this.value = value;
        }

        @JsonValue
        public String xmlValue(){
            return this.value.toUpperCase();
        }
    }
    */

    //Good idea to use this type?
    @JacksonXmlProperty(localName = "partyCode")
    private String partyCode;
    @JacksonXmlProperty(localName = "votes")
    private int votes;
    @JacksonXmlProperty(localName = "share")
    private double share;

    public ResultModel(){}

    public String getPartyCode() {
        return partyCode;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getShare() {
        return share;
    }

    public void setShare(double share) {
        this.share = share;
    }
}
