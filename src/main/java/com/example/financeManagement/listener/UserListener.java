package com.example.financeManagement.listener;

import com.example.financeManagement.model.Transaction;
import com.example.financeManagement.model.User;
import com.example.financeManagement.model.type.TransactionType;
import com.example.financeManagement.repository.TransactionRepository;
import com.example.financeManagement.util.SpringContext;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class UserListener {

    @PostUpdate
    public void postUpdate(User user) {
        if(!Objects.equals(user.getBalance(), user.getPreviousBalance())){
            performTransaction(user);
        }
    }
    private void performTransaction(User user) {
        TransactionRepository transactionRepository = SpringContext.getBean(TransactionRepository.class);

        Transaction transaction = Transaction
                .builder()
                .user(user)
                .transactionType(getTransactionType(user))
                .amount(user.getBalance() - user.getPreviousBalance())
                .timestamp(Instant.now())
                .balanceAfterTransaction(user.getBalance())
                .build();

        transactionRepository.save(transaction);
    }

    private TransactionType getTransactionType(User user) {
        if(user.getPreviousBalance() > user.getBalance()){
            return TransactionType.WITHDRAWAL;
        }

        return TransactionType.DEPOSIT;

    }

}
