package com.example.restapi_skillfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "operations")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "operation_type", nullable = false)
    @Enumerated
    private OperationType typeOperation;
    @Column(name = "operation_amount", nullable = false)
    private BigDecimal amountOperation;
    @Column(name = "operation_date", nullable = false)
    private LocalDate localDate;
    @ManyToOne
    @JoinColumn(name = "user_balance_id", nullable = false)
    private UserBalance userBalance;
    @ManyToOne
    @JoinColumn(name = "user_balance_transfer_id")
    private UserBalance userBalanceTransferTo;

}
