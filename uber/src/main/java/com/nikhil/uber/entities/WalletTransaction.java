package com.nikhil.uber.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nikhil.uber.entities.enums.TransactionMethod;
import com.nikhil.uber.entities.enums.TransactionType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(indexes = {
        @Index(name = "idx_wallet_transaction_wallet", columnList = "wallet_id"),
        @Index(name = "idx_wallet_transaction_ride", columnList = "ride_id")
})
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    //(CREDIT,BANKING) || (DEBIT,RIDE)
    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    @ManyToOne
    private Ride ride;

    private String transactionId; //Buisness Logic

    @ManyToOne
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;
}


/*
 * A record of every financial activity (credit or debit) in the wallet.
 * Real-Life Example: When you book a ride costing ₹200 and pay using your Uber
 * wallet:
 * 
 * A WalletTransaction is created to show ₹200 was debited for this ride. If you
 * later add ₹500 to the wallet using a credit card, another WalletTransaction
 * is created showing ₹500 was credited.
 * 
 */

/*
 * 
 * How It’s Used in Real Life Let’s assume you are using the Uber app:
 * 
 * BANKING Example:
 * 
 * You add ₹500 to your Uber Wallet using a credit card. TransactionMethod:
 * BANKING TransactionType: CREDIT RIDE Example:
 * 
 * You take a ride costing ₹300, and it is deducted from your wallet.
 * TransactionMethod: RIDE TransactionType: DEBIT
 * 
 * 
 */