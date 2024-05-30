package com.example.restapi_skillfactory.service;

import com.example.restapi_skillfactory.dto.UserBalanceDTO;
import com.example.restapi_skillfactory.exception.NegativeAmount;
import com.example.restapi_skillfactory.exception.NoSuchElementException;
import com.example.restapi_skillfactory.exception.NotEnoughMoney;
import com.example.restapi_skillfactory.mapper.UserBalanceMapper;
import com.example.restapi_skillfactory.model.Operation;
import com.example.restapi_skillfactory.model.OperationType;
import com.example.restapi_skillfactory.model.UserBalance;
import com.example.restapi_skillfactory.repository.OperationRepository;
import com.example.restapi_skillfactory.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;
    private final OperationRepository operationRepository;
    private final UserBalanceMapper userBalanceMapper;

    public List<UserBalance> getAllUserBalance() {
        return userBalanceRepository.findAll();
    }

    public UserBalanceDTO getUserBalanceById(Integer id) {
        return userBalanceMapper.mapToUserBalanceDTO(userBalanceRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователя с" +
                        " ID = " + id + " не существует")));
    }

    public UserBalance saveUserBalance(UserBalanceDTO userBalanceDTO) {
        UserBalance userBalance = userBalanceMapper.mapToUserBalance(userBalanceDTO);
        return userBalanceRepository.save(userBalance);
    }

    public UserBalanceDTO putMonet(Integer id, BigDecimal amount) throws NegativeAmount {
        UserBalanceDTO userBalanceDTO = getUserBalanceById(id);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmount("Вы ввели сумму меньше 0");
        }
        userBalanceDTO.setBalance(userBalanceDTO.getBalance().add(amount));
        Operation operation = new Operation();
        operation.setAmountOperation(amount);
        operation.setLocalDate(LocalDate.now());
        operation.setTypeOperation(OperationType.PUT_MONEY);
        operation.setUserBalance(userBalanceMapper.mapToUserBalance(userBalanceDTO));
        UserBalance savedUserBalance = userBalanceRepository.save(userBalanceMapper.mapToUserBalance(userBalanceDTO));
        operationRepository.save(operation);
        return userBalanceMapper.mapToUserBalanceDTO(savedUserBalance);
    }

    public UserBalanceDTO takeMoney(Integer id, BigDecimal amount) throws NotEnoughMoney, NegativeAmount {
        UserBalanceDTO userBalanceDTO = getUserBalanceById(id);
        BigDecimal balance = userBalanceDTO.getBalance();
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmount("Вы ввели сумму меньше 0");
        } else if (balance.compareTo(amount) < 0) {
            throw new NotEnoughMoney("Недостаточно средств");
        }
        userBalanceDTO.setBalance(userBalanceDTO.getBalance().subtract(amount));
        Operation operation = new Operation();
        operation.setAmountOperation(amount);
        operation.setLocalDate(LocalDate.now());
        operation.setTypeOperation(OperationType.TAKE_MONEY);
        operation.setUserBalance(userBalanceMapper.mapToUserBalance(userBalanceDTO));
        UserBalance savedUserBalance = userBalanceRepository.save(userBalanceMapper.mapToUserBalance(userBalanceDTO));
        operationRepository.save(operation);
        return userBalanceMapper.mapToUserBalanceDTO(savedUserBalance);
    }

    public List<UserBalanceDTO> tranferMoney(Integer from, Integer to, BigDecimal amount) throws NegativeAmount, NotEnoughMoney {
        List<UserBalanceDTO> userBalanceDTOlist = new ArrayList<>();
        UserBalance userBalanceFrom = userBalanceRepository.getReferenceById(from);
        UserBalance userBalanceTo = userBalanceRepository.getReferenceById(to);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmount("Вы ввели сумму меньше 0");
        } else if (userBalanceFrom.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughMoney("Недостаточно средств");
        }
        userBalanceFrom.setBalance(userBalanceFrom.getBalance().subtract(amount));
        userBalanceTo.setBalance(userBalanceTo.getBalance().add(amount));
        Operation operation = new Operation();
        operation.setAmountOperation(amount);
        operation.setLocalDate(LocalDate.now());
        operation.setTypeOperation(OperationType.TRANSFER_MONEY);
        operation.setUserBalance(userBalanceFrom);
        operation.setUserBalanceTransferTo(userBalanceTo);
        operationRepository.save(operation);
        UserBalanceDTO saveUserBalanceDTOfrom = userBalanceMapper.mapToUserBalanceDTO(userBalanceRepository.save(userBalanceFrom));
        UserBalanceDTO savedUserBalanceDTOto = userBalanceMapper.mapToUserBalanceDTO(userBalanceRepository.save(userBalanceTo));
        userBalanceDTOlist.add(saveUserBalanceDTOfrom);
        userBalanceDTOlist.add(savedUserBalanceDTOto);
        return userBalanceDTOlist;
    }
}
