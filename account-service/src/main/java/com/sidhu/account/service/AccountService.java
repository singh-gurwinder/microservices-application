package com.sidhu.account.service;

import com.sidhu.account.entity.Account;
import com.sidhu.account.repository.AccountRepository;
import com.sidhu.account.vo.AccountVO;
import com.sidhu.account.vo.TransactionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public List<Account> getAllAccounts() {
        log.info("AccountService: Get all accounts.");
        return accountRepository.findAll();
    }

    public Account save(Account account) {
        log.info("AccountService: Save account.");
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        log.info("AccountService: Get account by Id: " + id);
        return accountRepository.getByAccountId(id);
    }

    public List<Account> getAccountsByUserId(Long userId) {
        log.info("AccountService: Get accounts by User Id: " + userId);
        return accountRepository.getByUserId(userId);
    }

    public AccountVO getAccountTransactions(Long accountId) {
        log.info("AccountService: Get transactions for Account Id: " + accountId);
        Account account = accountRepository.getByAccountId(accountId);
        AccountVO accountVO = new AccountVO();
        if (account != null) {
            accountVO.setAccount(account);
            Boolean result = circuitBreakerFactory.create("transaction-service").run(() -> {
                ResponseEntity<TransactionVO[]> response = restTemplate.getForEntity("http://TRANSACTION-SERVICE/transactions/account/" + accountId,
                        TransactionVO[].class);
                accountVO.setTransactions(List.of(response.getBody()));
                return true;
            }, throwable -> false);
            if (!result) {
                accountVO.setError("Transaction Service is down");
            }
        }
        return accountVO;
    }
}
