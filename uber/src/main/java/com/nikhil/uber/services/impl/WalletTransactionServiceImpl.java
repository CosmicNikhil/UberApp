package com.nikhil.uber.services.impl;

import org.springframework.stereotype.Service;

import com.nikhil.uber.entities.WalletTransaction;
import com.nikhil.uber.repositories.WalletTransactionRepository;
import com.nikhil.uber.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }

}
