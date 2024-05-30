package com.example.restapi_skillfactory.mapper;

import com.example.restapi_skillfactory.dto.UserBalanceDTO;
import com.example.restapi_skillfactory.model.UserBalance;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceMapper {
    public UserBalanceDTO mapToUserBalanceDTO(UserBalance userBalance) {
        UserBalanceDTO userBalanceDTO = new UserBalanceDTO();
        userBalanceDTO.setId(userBalance.getId());
        userBalanceDTO.setBalance(userBalance.getBalance());
        return userBalanceDTO;
    }

    public UserBalance mapToUserBalance(UserBalanceDTO userBalanceDTO) {
        UserBalance userBalance = new UserBalance();
        userBalance.setId(userBalanceDTO.getId());
        userBalance.setBalance(userBalanceDTO.getBalance());
        return userBalance;
    }
}
