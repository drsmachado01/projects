package br.com.darlan.kotlinusersapi.util

import br.com.darlan.kotlinusersapi.controller.UserController
import br.com.darlan.kotlinusersapi.dto.UserDTO
import br.com.darlan.kotlinusersapi.model.User
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class UserUtil {
    fun  dtoToEntity(dto: UserDTO): User {
        return User(dto.id, dto.username, dto.password, dto.email, dto.status, dto.registerDate);
    }

    fun  entityToDTO(entity: User): UserDTO {
        return UserDTO(entity.id, entity.username, entity.password, entity.email, entity.status, entity.registerDate);
    }

    fun dtoListToEntityList(dtos: List<UserDTO>): List<User> {
        return dtos.stream().map {
                d -> dtoToEntity(d)
        }.toList()
    }

    fun entityListToDTOList(entities: List<User>): List<UserDTO> {
        return entities.stream().map {
                e -> entityToDTO(e)
        }.toList()
    }

    fun addSelfLink(dto: UserDTO): UserDTO {
        return dto.add(linkTo(methodOn(UserController::class.java).findById(dto.id)).withSelfRel())
    }

    fun addSelfLink(dtoList: List<UserDTO>): List<UserDTO> {
        return dtoList.stream().peek { dto -> run {addSelfLink(dto)} }.toList()
    }
}