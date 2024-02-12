package br.com.darlan.tasks.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "TB_TASKS")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTask: Long,
    var taskName: String,
    var taskDescription: String,
    var uniqueExecution: Boolean,
    var startDate: LocalDate,
    var startTime: LocalTime,
    var endDate: LocalDate,
    var endTime: LocalTime,
    var address: String,
    var host: String)
