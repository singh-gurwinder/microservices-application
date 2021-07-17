package com.sidhu.transaction.service;

import com.sidhu.transaction.entity.Transaction;
import com.sidhu.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> allTransactions() {
        log.info("TransactionService: Get all transactions.");
        return transactionRepository.findAll();
    }

    public Transaction save(Transaction transaction) {
        log.info("TransactionService: Save transaction.");
        transaction.setTransactionDate(new Date());
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        log.info("TransactionService: Get transaction by Id: " + id);
        return transactionRepository.getByTransactionId(id);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        log.info("TransactionService: Get all transactions for Account Id: " + accountId);
        return transactionRepository.getByAccountId(accountId);
    }
}
