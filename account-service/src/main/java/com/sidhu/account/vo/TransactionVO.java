package com.sidhu.account.vo;

import com.sidhu.account.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVO {
    private Long transactionId;
    private Long accountId;
    private Double amount;
    private TransactionType transactionType;
    private Date transactionDate;
}
