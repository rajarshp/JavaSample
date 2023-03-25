package com.mycode.foodorderapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long transactionId;

    @Column
    @NotNull
    private double amount;

    @Column
    @NotNull
    private String transactionStatus;

    @Column
    @NotNull
    private LocalDateTime transactionDate;


}
