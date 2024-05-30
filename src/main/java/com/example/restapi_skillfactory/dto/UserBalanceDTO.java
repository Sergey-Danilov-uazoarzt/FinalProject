package com.example.restapi_skillfactory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserBalanceDTO {
    private Integer id;
    private BigDecimal balance;
}
