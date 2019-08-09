package com.tim.model;

import org.springframework.stereotype.Service;

import javax.persistence.Id;

@Service
public class Result {
    @Id
    private Integer id;
    private String constituencyName;

    private Integer votes;
    private Double share;

    public Result(){}

    public Result(Integer id, String constituencyName, Integer votes, Double share) {
        this.id = id;
        this.constituencyName = constituencyName;
        this.votes = votes;
        this.share = share;
    }

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

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }
}
