package com.tim.model;

import org.springframework.stereotype.Service;

@Service
public interface Result {
    Integer getId();

    void setId(Integer id);

    String getConstituencyName();

    void setConstituencyName(String constituencyName);

    Integer getVotes();

    void setVotes(Integer votes);

    Double getShare();

    void setShare(Double share);
}
