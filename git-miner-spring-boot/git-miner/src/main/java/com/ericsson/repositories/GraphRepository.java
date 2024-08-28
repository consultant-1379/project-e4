package com.ericsson.repositories;

import com.ericsson.collections.GraphCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends MongoRepository<GraphCollection, String> {
}
