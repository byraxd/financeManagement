package com.example.financeManagement.model;

import com.example.financeManagement.model.type.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.security.Timestamp;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated
    @Column(name = "type", nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Double amount;

    @CreatedDate
    @Column(nullable = false)
    private Instant timestamp;

    @Column(nullable = false)
    private Double balanceAfterTransaction;

}
