package com.casava.library.repository;

import com.casava.library.model.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends MongoRepository<LoanEntity, String> {
}
