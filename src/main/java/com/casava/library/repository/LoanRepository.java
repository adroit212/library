package com.casava.library.repository;

import com.casava.library.model.LoanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends MongoRepository<LoanEntity, String> {
    List<LoanEntity> findAllByUserIdOrderByLoanDateDesc(String userId);
}
