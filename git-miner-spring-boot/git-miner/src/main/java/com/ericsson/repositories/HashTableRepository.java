package com.ericsson.repositories;

import com.ericsson.collections.HashTableCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTableRepository extends MongoRepository<HashTableCollection, String> {
}
