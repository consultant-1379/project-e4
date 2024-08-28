package com.ericsson.repositories;

import com.ericsson.collections.ContributorsCountCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContributorsCountRepository extends MongoRepository<ContributorsCountCollection, String> {
}
