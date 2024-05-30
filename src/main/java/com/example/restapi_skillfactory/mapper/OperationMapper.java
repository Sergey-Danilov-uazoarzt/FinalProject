package com.example.restapi_skillfactory.mapper;

import com.example.restapi_skillfactory.dto.OperationDTO;
import com.example.restapi_skillfactory.model.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {
    public OperationDTO mapToOperationDTO(Operation operation) {
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(operation.getId());
        operationDTO.setAmountOperation(operation.getAmountOperation());
        operationDTO.setLocalDate(operation.getLocalDate());
        operationDTO.setTypeOperation(operation.getTypeOperation());
        operationDTO.setUserBalance(operation.getUserBalance());
        operationDTO.setUserBalanceTransferTo(operation.getUserBalanceTransferTo());
        return operationDTO;
    }

    public Operation mapToOperation(OperationDTO operationDTO) {
        Operation operation = new Operation();
        operation.setId(operationDTO.getId());
        operation.setAmountOperation(operationDTO.getAmountOperation());
        operation.setLocalDate(operationDTO.getLocalDate());
        operation.setTypeOperation(operationDTO.getTypeOperation());
        operation.setUserBalance(operationDTO.getUserBalance());
        operation.setUserBalanceTransferTo(operationDTO.getUserBalanceTransferTo());
        return operation;
    }
}
