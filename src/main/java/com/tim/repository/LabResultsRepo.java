package com.tim.repository;

import com.tim.model.LabResults;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

public interface LabResultsRepo extends CrudRepository<LabResults, Integer> {
}
