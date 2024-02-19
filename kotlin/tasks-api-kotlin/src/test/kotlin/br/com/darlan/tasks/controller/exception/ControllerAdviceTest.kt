package br.com.darlan.tasks.controller.exception

import br.com.darlan.tasks.service.exception.NotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private const val EXCEPTON_NOT_FOUND_MSG = "There's no task associated to the id 1"

class ControllerAdviceTest{

    @Test
    fun notFoundExceptionHandler() {
        assertThrows<NotFoundException> {
            throw NotFoundException(EXCEPTON_NOT_FOUND_MSG)
        }
    }
}