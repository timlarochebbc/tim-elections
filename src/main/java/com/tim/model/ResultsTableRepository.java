package com.tim.model;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultsTableRepository extends CrudRepository<ResultsTable, Integer> {
}
