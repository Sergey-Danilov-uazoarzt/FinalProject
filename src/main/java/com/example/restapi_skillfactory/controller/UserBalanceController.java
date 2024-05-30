package com.example.restapi_skillfactory.controller;

import com.example.restapi_skillfactory.dto.OperationDTO;
import com.example.restapi_skillfactory.dto.UserBalanceDTO;
import com.example.restapi_skillfactory.exception.NegativeAmount;
import com.example.restapi_skillfactory.exception.NotEnoughMoney;
import com.example.restapi_skillfactory.service.OperationService;
import com.example.restapi_skillfactory.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class UserBalanceController {

    private final UserBalanceService userBalanceService;
    private final OperationService operationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBalanceDTO> getBalance(@PathVariable("id") Integer id) {
        UserBalanceDTO userBalanceDTOById = userBalanceService.getUserBalanceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userBalanceDTOById);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OperationDTO>> getOperationList(@PathVariable("id") Integer id,
                                                               @RequestParam(value = "start", required = false) LocalDate start,
                                                               @RequestParam(value = "end", required = false) LocalDate end) {
        List<OperationDTO> operationDTOList = operationService.getOperationsFiltered(id, start, end);
        return ResponseEntity.ok().body(operationDTOList);
    }

    @RequestMapping(value = "/putMoney/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBalanceDTO> putMoney(@PathVariable("id") Integer id,
                                                   @RequestParam(value = "putMoney") BigDecimal amount) throws NotEnoughMoney, NegativeAmount {
        UserBalanceDTO savedUserBalanceDTO = userBalanceService.putMonet(id, amount);
        return ResponseEntity.status(HttpStatus.OK).body(savedUserBalanceDTO);
    }

    @RequestMapping(value = "/takeMoney/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBalanceDTO> takeMoney(@PathVariable("id") Integer id,
                                                    @RequestParam(value = "takeMoney") BigDecimal amount) throws NotEnoughMoney, NegativeAmount {
        UserBalanceDTO savedUserBalanceDTO = userBalanceService.takeMoney(id, amount);
        return ResponseEntity.status(HttpStatus.OK).body(savedUserBalanceDTO);
    }

    @RequestMapping(value = "/transferMoney", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserBalanceDTO>> transferMoney(@RequestParam(value = "from") Integer fromId,
                                                              @RequestParam(value = "to") Integer toId,
                                                              @RequestParam(value = "amount") BigDecimal amount) throws NegativeAmount, NotEnoughMoney {
        List<UserBalanceDTO> userBalanceList = userBalanceService.tranferMoney(fromId, toId, amount);
        return ResponseEntity.status(HttpStatus.OK).body(userBalanceList);

    }
}
