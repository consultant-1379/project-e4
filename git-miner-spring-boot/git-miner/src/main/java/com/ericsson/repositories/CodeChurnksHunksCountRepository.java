package com.ericsson.repositories;

import com.ericsson.collections.CodeChurnAndHunksCountCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeChurnksHunksCountRepository extends MongoRepository<CodeChurnAndHunksCountCollection, String> {
}
