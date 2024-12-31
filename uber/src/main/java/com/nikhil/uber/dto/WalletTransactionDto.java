package com.nikhil.uber.dto;

import java.time.LocalDateTime;

import com.nikhil.uber.entities.enums.TransactionMethod;
import com.nikhil.uber.entities.enums.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    /*CREDIT, DEBIT*/
    private TransactionType transactionType;

    /*BANKING, RIDE*/
    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;

}

//(CREDIT,BANKING) || (DEBIT,RIDE)