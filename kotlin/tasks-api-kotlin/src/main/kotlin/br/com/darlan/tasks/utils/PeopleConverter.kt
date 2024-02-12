package br.com.darlan.tasks.utils

import br.com.darlan.tasks.dto.PersonDTO
import br.com.darlan.tasks.model.Person

class PeopleConverter {
    fun entityToDTO(entity: Person): PersonDTO {
        return PersonDTO(entity.idPerson, entity.name, entity.phoneNumber)
    }

    fun dtoToEntity(dto: PersonDTO): Person {
        return Person(dto.idPerson, dto.name, dto.name)
    }

    fun entityListToDTOList(entities: List<Person>): List<PersonDTO> {
        return entities.stream().map {
                p -> entityToDTO(p)
        }.toList()
    }

    fun dtoListToEntityList(dtos: List<PersonDTO>): List<Person> {
        return dtos.stream().map {
                d -> dtoToEntity(d)
        }.toList()
    }
}