package com.sidhu.transaction.entity;

import com.sidhu.transaction.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    @Min(value = 1, message = "Account id must be more than 0.")
    private Long accountId;
    private Double amount;
    private TransactionType transactionType;
    private Date transactionDate;
}
