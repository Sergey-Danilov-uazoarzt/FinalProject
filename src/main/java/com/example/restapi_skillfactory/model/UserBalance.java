package com.example.restapi_skillfactory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "user_balance")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "balance", nullable = false, columnDefinition = "Decimal(10,2)")
    private BigDecimal balance;
}
