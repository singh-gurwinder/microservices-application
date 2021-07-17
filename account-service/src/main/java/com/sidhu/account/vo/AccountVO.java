package com.sidhu.account.vo;

import com.sidhu.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {
    private Account account;
    private List<TransactionVO> transactions;
    private String error;
}
