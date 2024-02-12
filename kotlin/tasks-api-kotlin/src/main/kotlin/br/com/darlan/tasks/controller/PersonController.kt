package br.com.darlan.tasks.controller

import br.com.darlan.tasks.dto.PersonDTO
import br.com.darlan.tasks.service.PersonService
import br.com.darlan.tasks.utils.PeopleConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/people")
class PersonController {
    private lateinit var service: PersonService
    private lateinit var converter: PeopleConverter

    @Autowired
    fun PersonController(service: PersonService,
                         converter: PeopleConverter) {
        this.service = service
        this.converter = converter
    }

    @GetMapping
    fun list(): ResponseEntity<List<PersonDTO>> {
        return ResponseEntity.ok(converter.entityListToDTOList(service.list()))
    }

    @PostMapping
    fun save(@RequestBody dto: PersonDTO): ResponseEntity<PersonDTO> {
        return ResponseEntity.ok(converter.entityToDTO(service.save(converter.dtoToEntity(dto))))
    }

    @GetMapping("/{idPerson}")
    fun findById(@PathVariable("idPerson") idPerson: Long): ResponseEntity<PersonDTO> {
        return ResponseEntity.ok(converter.entityToDTO(service.findById(idPerson)))
    }

    @PutMapping("/{idPerson}")
    fun update(@PathVariable("idPerson") idPerson: Long, @RequestBody dto: PersonDTO): ResponseEntity<PersonDTO> {
        return ResponseEntity.ok(converter.entityToDTO(service.update(idPerson, converter.dtoToEntity(dto))))
    }

    @DeleteMapping("/{idPerson}")
    fun delete(@PathVariable("idPerson") idPerson: Long): ResponseEntity<String> {
        service.delete(idPerson)
        return ResponseEntity.ok("Person associated to id $idPerson has been deleted")
    }
}