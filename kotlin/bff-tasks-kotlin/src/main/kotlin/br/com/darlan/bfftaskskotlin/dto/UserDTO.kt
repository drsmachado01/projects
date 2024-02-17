package br.com.darlan.bfftaskskotlin.dto

import java.time.LocalDate

class UserDTO {
    var id: Long? = null;
    var username: String? = null;
    var email: String? = null;
    var status: Boolean? = null;
    var registerDate: LocalDate? = null;
}