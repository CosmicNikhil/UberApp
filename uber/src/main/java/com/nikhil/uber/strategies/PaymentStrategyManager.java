package com.nikhil.uber.strategies;

import org.springframework.stereotype.Component;

import com.nikhil.uber.entities.enums.PaymentMethod;
import com.nikhil.uber.strategies.impl.CashPaymentStrategy;
import com.nikhil.uber.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
