package br.com.darlan.tasks.model

import jakarta.persistence.*

@Entity
@Table(name = "TB_PEOPLE")
data class Person(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idPerson: Long,
    var name: String,
    var phoneNumber: String)

