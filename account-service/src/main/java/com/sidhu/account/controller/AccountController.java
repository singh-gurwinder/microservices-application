package com.sidhu.account.controller;

import com.sidhu.account.entity.Account;
import com.sidhu.account.service.AccountService;
import com.sidhu.account.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("{id}/transactions")
    public AccountVO getAccountTransactions(@PathVariable("id") final Long accountId) {
        log.info("AccountController: Get transactions for Account Id: " + accountId);
        return accountService.getAccountTransactions(accountId);
    }

    @GetMapping
    public List<Account> allAccounts() {
        log.info("AccountController: Get all accounts.");
        return accountService.getAllAccounts();
    }

    @PostMapping
    public Account save(@RequestBody Account account) {
        log.info("AccountController: Save account.");
        return accountService.save(account);
    }

    @GetMapping("/user/{id}")
    public List<Account> getAccountsByUserId(@PathVariable("id") final Long userId) {
        log.info("AccountController: Get accounts for User Id: " + userId);
        return accountService.getAccountsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") final Long id) {
        log.info("AccountController: Get account for Id: " + id);
        return accountService.getAccountById(id);
    }
}
