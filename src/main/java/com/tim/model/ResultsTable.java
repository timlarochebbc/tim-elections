package com.tim.model;


import javax.persistence.*;

@Entity
public class ResultsTable {
    @Id
    private Integer id;

    public ResultsTable(){}

    public ResultsTable(Integer id, String constituencyName, Integer labVotes, Integer conVotes, Integer ldVotes, Integer pcVotes, Integer othVotes, Integer grnVotes, Integer ukipVotes) {
        this.id = id;
        this.constituencyName = constituencyName;
        this.labVotes = labVotes;
        this.conVotes = conVotes;
        this.ldVotes = ldVotes;
        this.pcVotes = pcVotes;
        this.othVotes = othVotes;
        this.grnVotes = grnVotes;
        this.ukipVotes = ukipVotes;
    }

    private String constituencyName;

    private Integer labVotes;
    private Integer conVotes;
    private Integer ldVotes;
    private Integer pcVotes;
    private Integer othVotes;
    private Integer grnVotes;
    private Integer ukipVotes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public Integer getLabVotes() {
        return labVotes;
    }

    public void setLabVotes(Integer labVotes) {
        this.labVotes = labVotes;
    }

    public Integer getConVotes() {
        return conVotes;
    }

    public void setConVotes(Integer conVotes) {
        this.conVotes = conVotes;
    }

    public Integer getLdVotes() {
        return ldVotes;
    }

    public void setLdVotes(Integer ldVotes) {
        this.ldVotes = ldVotes;
    }

    public Integer getPcVotes() {
        return pcVotes;
    }

    public void setPcVotes(Integer pcVotes) {
        this.pcVotes = pcVotes;
    }

    public Integer getOthVotes() {
        return othVotes;
    }

    public void setOthVotes(Integer othVotes) {
        this.othVotes = othVotes;
    }

    public Integer getGrnVotes() {
        return grnVotes;
    }

    public void setGrnVotes(Integer grnVotes) {
        this.grnVotes = grnVotes;
    }

    public Integer getUkipVotes() {
        return ukipVotes;
    }

    public void setUkipVotes(Integer ukipVotes) {
        this.ukipVotes = ukipVotes;
    }
}
