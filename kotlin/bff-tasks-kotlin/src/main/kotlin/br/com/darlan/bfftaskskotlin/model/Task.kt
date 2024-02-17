package br.com.darlan.bfftaskskotlin.model

import java.time.LocalDate
import java.time.LocalTime

class Task {
    var idTask: Long? = null;
    var taskName: String? = null;
    var taskDescription: String? = null;
    var uniqueExecution: Boolean? = null;
    var startDate: LocalDate? = null;
    var startTime: LocalTime? = null;
    var endDate: LocalDate? = null;
    var endTime: LocalTime? = null;
    var address: String? = null;
    var host: String? = null;
}