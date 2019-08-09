package com.tim.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GrnResults extends Result {
    @Id
    private Integer id;
    private String constituencyName;

    private Integer votes;
    private Double share;

    public GrnResults(){}

    public GrnResults(Integer id, String constituencyName, Integer votes, Double share) {
        this.id = id;
        this.constituencyName = constituencyName;
        this.votes = votes;
        this.share = share;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getConstituencyName() {
        return constituencyName;
    }

    @Override
    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    @Override
    public Integer getVotes() {
        return votes;
    }

    @Override
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public Double getShare() {
        return share;
    }

    @Override
    public void setShare(Double share) {
        this.share = share;
    }
}
