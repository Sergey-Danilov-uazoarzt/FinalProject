package com.example.restapi_skillfactory.exception;

import com.example.restapi_skillfactory.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchElementException exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotEnoughMoney.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ErrorDTO handleException(NotEnoughMoney exception) {
        return proceedFieldsErrors(exception, exception.getMessage(), "Ошибка при снятии наличных");
    }

    @ExceptionHandler(NegativeAmount.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ErrorDTO handleException(NegativeAmount exception) {
        return proceedFieldsErrors(exception, exception.getMessage(), "Ошибка операции");
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception) {
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    private ErrorDTO proceedFieldsErrors(Exception ex,
                                         String error,
                                         String description) {
        ErrorDTO errorDTO = new ErrorDTO(error, description);
        errorDTO.add(ex.getClass().getName(), "", errorDTO.getMessage());
        return errorDTO;
    }
}
