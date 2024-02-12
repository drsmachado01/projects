package br.com.darlan.tasks.dto

import java.time.LocalDate
import java.time.LocalTime

data class TaskDTO(var idTask: Long,
                   var taskName: String,
                   var taskDescription: String,
                   var uniqueExecution: Boolean,
                   var startDate: LocalDate,
                   var startTime: LocalTime,
                   var endDate: LocalDate,
                   var endTime: LocalTime,
                   var address: String,
                   var host: String)
