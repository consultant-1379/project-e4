package com.ericsson.repositories;

import com.ericsson.collections.RepoTableCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRepository extends MongoRepository<RepoTableCollection, String> {
}
