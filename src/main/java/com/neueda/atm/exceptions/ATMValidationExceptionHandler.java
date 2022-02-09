package com.neueda.atm.exceptions;

import com.neueda.atm.models.ErrorMessage;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@ControllerAdvice
public class ATMValidationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseBody  ErrorMessage validationExceptionHandler(ValidationException e) {
        return new ErrorMessage(e.getMessage());
    }

}