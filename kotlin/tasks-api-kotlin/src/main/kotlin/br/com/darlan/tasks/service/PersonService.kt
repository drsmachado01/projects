package br.com.darlan.tasks.service

import br.com.darlan.tasks.model.Person
import br.com.darlan.tasks.repository.PersonRepository
import br.com.darlan.tasks.service.exception.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonService {
    private lateinit var personRepository: PersonRepository

    @Autowired
    fun PersonService(personRepository: PersonRepository) {
        this.personRepository = personRepository
    }

    fun save(person: Person): Person {
        return personRepository.save(person)
    }

    fun list(): MutableList<Person> {
        return personRepository.findAll()
    }

    fun findById(idPerson: Long): Person {
        return personRepository.findById(idPerson).orElse(null)
    }

    fun update(idPerson: Long, person: Person): Person {
        var person_: Person? = person(idPerson)

        person.idPerson = person_!!.idPerson

        return personRepository.save(person)
    }

    fun delete(idPerson: Long) {
        var person_: Person? = person(idPerson)

        personRepository.deleteById(idPerson)
    }

    private fun person(idPerson: Long): Person? =
        personRepository.findById(idPerson).orElseThrow {
            NotFoundException("There`s no person associated to the id: $idPerson")
        }
}