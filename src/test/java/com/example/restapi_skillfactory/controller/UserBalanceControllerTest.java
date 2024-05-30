package com.example.restapi_skillfactory.controller;

import com.example.restapi_skillfactory.dto.UserBalanceDTO;
import com.example.restapi_skillfactory.mapper.OperationMapper;
import com.example.restapi_skillfactory.mapper.UserBalanceMapper;
import com.example.restapi_skillfactory.repository.OperationRepository;
import com.example.restapi_skillfactory.repository.UserBalanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class UserBalanceControllerTest extends CommonTest {
    @Autowired
    private UserBalanceRepository userBalanceRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserBalanceMapper userBalanceMapper;
    @Autowired
    private OperationMapper operationMapper;


    @Override
    @Test
    @Order(0)
    @DisplayName("GET /balance/{id} - получение баланса пользователя по ID")
    protected void getBalance() throws Exception {
        UserBalanceDTO userBalanceDTO = userBalanceMapper.mapToUserBalanceDTO(userBalanceRepository.findById(1).get());
        int id = userBalanceDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.get("/balance/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userBalanceDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(1)
    @DisplayName("GET balance/list/{id} - получение списка операций пользователя за период")
    protected void getOperationList() throws Exception {
        int id = 1;
        String result = mvc.perform(MockMvcRequestBuilders.get("/balance/list/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);


    }

    @Override
    @Test
    @Order(2)
    @DisplayName("PUT balance/putMoney/{id} - пополнение баланса пользователя")
    protected void putMoney() throws Exception {
        int id = 2;
        String result = mvc.perform(MockMvcRequestBuilders.put("/balance/putMoney/{id}?putMoney=100.20", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(3)
    @DisplayName("PUT balance/takeMoney/{id} - снятие денег с баланса пользователя")
    protected void takeMoney() throws Exception {
        int id = 2;
        String result = mvc.perform(MockMvcRequestBuilders.put("/balance/takeMoney/{id}?takeMoney=40", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(3)
    @DisplayName("PUT balance/transferMoney?from=...&to=...&amount=... - снятие денег с баланса пользователя")
    protected void transferMoney() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.put("/balance/transferMoney?from=2&to=1&amount=100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }
}
