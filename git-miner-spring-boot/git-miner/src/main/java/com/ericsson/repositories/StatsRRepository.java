package com.ericsson.repositories;

import com.ericsson.collections.StatsRepositoryGraphCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRRepository extends MongoRepository<StatsRepositoryGraphCollection, String> {
}
