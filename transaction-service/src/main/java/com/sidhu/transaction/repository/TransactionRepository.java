package com.sidhu.transaction.repository;

import com.sidhu.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction getByTransactionId(Long id);

    List<Transaction> getByAccountId(Long accountId);
}
