package br.com.darlan.kotlinusersapi.controller.exception

import br.com.darlan.kotlinusersapi.aspect.annotations.LogExecution
import br.com.darlan.kotlinusersapi.service.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler
    @LogExecution
    fun notFoundExceptionHandler(ex: NotFoundException): ResponseEntity<ErrorMessageModel> {

        val errorMessage = ErrorMessageModel(
            HttpStatus.NOT_FOUND.value(),
            ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }
}