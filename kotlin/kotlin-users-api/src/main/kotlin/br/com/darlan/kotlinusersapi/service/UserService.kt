package br.com.darlan.kotlinusersapi.service

import br.com.darlan.kotlinusersapi.aspect.annotations.LogExecution
import br.com.darlan.kotlinusersapi.dto.UserDTO
import br.com.darlan.kotlinusersapi.model.User
import br.com.darlan.kotlinusersapi.repository.UserRepository
import br.com.darlan.kotlinusersapi.service.exception.NotFoundException
import br.com.darlan.kotlinusersapi.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    private lateinit var repo: UserRepository
    private lateinit var util: UserUtil

    @Autowired
    fun UserService(repo: UserRepository, util: UserUtil) {
        this.repo = repo
        this.util = util
    }

    @LogExecution
    fun save(dto: UserDTO): UserDTO {
        return convertToReturn(
            repo.save(util.dtoToEntity(dto))
        )
    }

    @LogExecution
    fun list(): List<UserDTO> {
        return util.entityListToDTOList(
            repo.findAll()
        )
    }

    @LogExecution
    fun findById(id: Long): UserDTO {
        return convertToReturn(
            repo.findById(id).orElseThrow {
                NotFoundException("There's no user associated to the id $id")
            }!!
        )
    }

    @LogExecution
    fun update(id: Long, dto: UserDTO): UserDTO {
        repo.findById(id).orElseThrow {
            NotFoundException("There's no user associated to the id $id")
        }!!

        dto.id = id

        return convertToReturn(
            repo.save(util.dtoToEntity(dto))
        )
    }

    @LogExecution
    fun delete(id: Long) {
        val user = repo.findById(id).orElseThrow {
            NotFoundException("There's no user associated to the id $id")
        }!!
        repo.delete(user)
    }

    @LogExecution
    private fun convertToReturn(entity: User): UserDTO {
        return util.entityToDTO(entity)
    }
}