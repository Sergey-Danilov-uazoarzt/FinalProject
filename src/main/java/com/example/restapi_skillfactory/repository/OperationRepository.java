package com.example.restapi_skillfactory.repository;

import com.example.restapi_skillfactory.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    @Query(nativeQuery = true,
            value = """
                       select * from (select *
                       from operations
                       where user_balance_id = :userBalanceId
                       and operation_date >= coalesce(:start, to_date('01.01.1900', 'dd.mm.yyyy'))
                       and operation_date <= coalesce(:end, (select max(operation_date) from operations))) "o*"
                    """)
    List<Operation> getOperationsByDates(Integer userBalanceId,
                                         LocalDate start,
                                         LocalDate end);
}
