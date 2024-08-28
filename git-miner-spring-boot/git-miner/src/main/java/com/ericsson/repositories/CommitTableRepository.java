package com.ericsson.repositories;

import com.ericsson.collections.CommitTableCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommitTableRepository extends MongoRepository<CommitTableCollection, String> {
}
