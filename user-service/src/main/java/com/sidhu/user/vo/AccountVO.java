package com.sidhu.user.vo;

import com.sidhu.user.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {
    private Long accountId;
    private Long userId;
    private Double accountBalance;
    private AccountType accountType;
}
