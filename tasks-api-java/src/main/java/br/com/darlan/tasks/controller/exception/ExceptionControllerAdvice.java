package br.com.darlan.tasks.controller.exception;

import br.com.darlan.tasks.aspect.annotations.LogExecution;
import br.com.darlan.tasks.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    @LogExecution
    public ResponseEntity<ErrorMessageModel> handleNotFoundException(
            NotFoundException ex, WebRequest req
    ) {
        ErrorMessageModel message = new ErrorMessageModel(
                404,
                ex.getMessage());

        return new ResponseEntity<ErrorMessageModel>(message, HttpStatus.NOT_FOUND);
    }
}
