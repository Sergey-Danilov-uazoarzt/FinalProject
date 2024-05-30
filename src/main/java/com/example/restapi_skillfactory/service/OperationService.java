package com.example.restapi_skillfactory.service;

import com.example.restapi_skillfactory.dto.OperationDTO;
import com.example.restapi_skillfactory.mapper.OperationMapper;
import com.example.restapi_skillfactory.model.Operation;
import com.example.restapi_skillfactory.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public List<OperationDTO> getOperationsFiltered(final Integer id,
                                                    final LocalDate startDate,
                                                    final LocalDate endDate) {
        List<Operation> operationsByDates = operationRepository.getOperationsByDates(id, startDate, endDate);
        List<OperationDTO> operationsDTObyDates = new ArrayList<>();
        for (Operation operation : operationsByDates) {
            OperationDTO operationDTO = operationMapper.mapToOperationDTO(operation);
            operationsDTObyDates.add(operationDTO);
        }
        return operationsDTObyDates;

    }
}
