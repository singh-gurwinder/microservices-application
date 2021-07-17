package com.sidhu.account.repository;

import com.sidhu.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getByAccountId(Long id);

    List<Account> getByUserId(Long userId);
}
