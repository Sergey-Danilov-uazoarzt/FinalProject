package com.example.restapi_skillfactory.dto;

import com.example.restapi_skillfactory.model.OperationType;
import com.example.restapi_skillfactory.model.UserBalance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OperationDTO {
    private Integer id;
    private OperationType typeOperation;
    private BigDecimal amountOperation;
    private LocalDate localDate;
    private UserBalance userBalance;
    private UserBalance userBalanceTransferTo;
}
