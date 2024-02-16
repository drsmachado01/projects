package br.com.darlan.kotlinusersapi.dto

import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate

data class UserDTO(
    var id: Long,
    val username: String,
    val password: String,
    val email: String,
    val status: Boolean,
    val registerDate: LocalDate): RepresentationModel<UserDTO>()