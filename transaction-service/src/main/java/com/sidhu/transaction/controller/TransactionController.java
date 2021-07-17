package com.sidhu.transaction.controller;

import com.sidhu.transaction.entity.Transaction;
import com.sidhu.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    public String test() {
        return "Transactions Service";
    }

    @GetMapping
    public List<Transaction> allTransactions() {
        log.info("TransactionController: Get all transactions.");
        return transactionService.allTransactions();
    }

    @PostMapping
    public Transaction save(@RequestBody Transaction transaction) {
        log.info("TransactionController: Save transaction.");
        return transactionService.save(transaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable("id") final Long id) {
        log.info("TransactionController: Get transaction by Id: " + id);
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/account/{id}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable("id") final Long accountId) {
        log.info("TransactionController: Get transactions by Account Id: " + accountId);
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
