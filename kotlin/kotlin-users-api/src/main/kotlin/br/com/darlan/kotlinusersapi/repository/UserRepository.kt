package br.com.darlan.kotlinusersapi.repository

import br.com.darlan.kotlinusersapi.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
}