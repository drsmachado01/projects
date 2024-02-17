package br.com.darlan.bfftaskskotlin.dto

import java.time.LocalDate

data class UserDTO(
    var id: Long,
    var username: String,
    var email: String,
    var status: Boolean,
    var registerDate: LocalDate)