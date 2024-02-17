package br.com.darlan.bfftaskskotlin.util

import br.com.darlan.bfftaskskotlin.aspect.annotations.LogExecution
import br.com.darlan.bfftaskskotlin.dto.UserDTO
import br.com.darlan.bfftaskskotlin.model.User
import org.springframework.stereotype.Component

@Component
class UserUtil {
    @LogExecution
    fun entityToDTO(user: User): UserDTO {
        return UserDTO(user.id!!,
            user.username!!,
            user.email!!,
            user.status!!,
            user.registerDate!!)
    }
}