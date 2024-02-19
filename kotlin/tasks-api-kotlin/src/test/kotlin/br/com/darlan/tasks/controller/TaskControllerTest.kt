package br.com.darlan.tasks.controller

import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.service.TaskService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import java.time.LocalTime

class TaskControllerTest {
    private lateinit var controller: TaskController
    private lateinit var service: TaskService

    private lateinit var taskDTO: TaskDTO

    @BeforeEach
    fun setUp() {
        service = mockk(relaxed = true)
        controller = TaskController(service)
        taskDTO = createDTOToSave("Task 1",
            "This is the first task",
            true,
            LocalDate.of(2024, 3, 1),
            LocalTime.of(10, 0),
            LocalDate.of(2024, 3, 1),
            LocalTime.of(10, 30),
            "Nowhere st, 10",
            1)
    }

    @Test
    fun list() {
        every { service.list() } returns listOf(taskDTO)

        assertEquals(controller.list(), ResponseEntity.ok(listOf(taskDTO)))
    }

    @Test
    fun list_emoty() {
        every { service.list() } returns ArrayList()

        var list: ResponseEntity<List<TaskDTO>>  = controller.list()
        assertNotNull(list)
        assertEquals(0, list.body!!.size)
    }

    @Test
    fun save() {
        every { service.save(taskDTO) } returns taskDTO
        assertEquals(controller.save(taskDTO), ResponseEntity.ok(taskDTO))
    }

    @Test
    fun findById() {
        every { service.findById(1) } returns taskDTO
        assertEquals(controller.findById(1), ResponseEntity.ok(taskDTO))
    }

    @Test
    fun update() {
        taskDTO.idTask = 1
        every { service.update(1, taskDTO) } returns taskDTO
        assertEquals(controller.update(1, taskDTO), ResponseEntity.ok(taskDTO))
    }

    @Test
    fun delete() {
        val idTask = 1
        assertEquals(controller.delete(1), ResponseEntity.ok("Task $idTask deleted"))
    }

    private fun createDTOToSave(
        taskName: String,
        taskDescription: String,
        uniqueExecution: Boolean,
        startDate: LocalDate,
        startTime: LocalTime,
        endDate: LocalDate,
        endtTime: LocalTime,
        address: String,
        host: Long
    ): TaskDTO {
        return TaskDTO(null, taskName, taskDescription, uniqueExecution, startDate, startTime, endDate, endtTime, address, host)
    }
}