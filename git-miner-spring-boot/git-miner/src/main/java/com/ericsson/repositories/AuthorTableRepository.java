package com.ericsson.repositories;

import com.ericsson.collections.AuthorTableCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorTableRepository extends MongoRepository<AuthorTableCollection, String> {
}
