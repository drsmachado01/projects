package br.com.darlan.tasks.service

import br.com.darlan.tasks.dto.TaskDTO
import br.com.darlan.tasks.model.Task
import br.com.darlan.tasks.repository.TaskRepository
import br.com.darlan.tasks.service.exception.NotFoundException
import br.com.darlan.tasks.utils.TaskUtil
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

private const val EXCEPTON_NOT_FOUND_MSG = "There's no task associated to the id 1"

class TaskServiceTest {
    private val repo: TaskRepository = mockk(relaxed = true)
    private val util: TaskUtil = mockk(relaxed = true)
    private val service = TaskService(repo, util)

    private lateinit var task: Task
    private lateinit var theDto: TaskDTO

    @BeforeEach
    fun setUp() {
        task = createEntity(1L,
            "Task 1",
            "This is the first task",
            true,
            LocalDate.of(2024, 3, 1),
            LocalTime.of(10, 0),
            LocalDate.of(2024, 3, 1),
            LocalTime.of(10, 30),
            "Nowhere st, 10",
            1)

        theDto = createDTOToSave("Task 1",
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
    fun save() {
        every { repo.save(any()) } returns task

        var dto_ = theDto
        val dto = service.save(dto = dto_)

        verify (exactly = 1) { repo.save(util.dtoToEntity(dto_)) }
        confirmVerified(dto)
    }

    @Test
    fun update_exception() {
        every { repo.findById(1) } throws NotFoundException(EXCEPTON_NOT_FOUND_MSG)

        val dto = theDto
        assertThrows<NotFoundException> { service.update(idTask = 1, dto = dto) }
    }

    @Test
    fun delete_exception() {
        every { repo.findById(1) } throws NotFoundException(EXCEPTON_NOT_FOUND_MSG)

        assertThrows<NotFoundException> { service.delete(idTask = 1) }
    }

    @Test
    fun findById_exception() {
        every { repo.findById(1) } throws NotFoundException(EXCEPTON_NOT_FOUND_MSG)

        assertThrows<NotFoundException> { service.findById(idTask = 1) }
    }

    @Test
    fun update() {
        every { repo.findById(1) } returns Optional.of(task)
        every { repo.save(any()) } returns task
        every { util.entityToDto(task) } returns theDto
        every { util.addSelfLink(theDto) } returns theDto

        val dto = service.update(idTask = 1, dto = theDto)

        verify (exactly = 1) { repo.save(util.dtoToEntity(theDto)) }
        Assertions.assertNotNull(dto)
        Assertions.assertEquals(theDto, dto)
    }

    @Test
    fun list() {
        every { repo.findAll() } returns listOf(task)
        every { util.entityListToDTOList(listOf(task)) } returns listOf(theDto)
        every { util.addSelfLink(listOf(theDto)) } returns listOf(theDto)

        val list = service.list()

        Assertions.assertNotNull(list)
        Assertions.assertEquals(1, list.size)
        Assertions.assertEquals(theDto, list[0])

        verify (exactly = 1) { repo.findAll() }
        verify (exactly = 1) { util.entityListToDTOList(listOf(task)) }
        verify (exactly = 1) { util.addSelfLink(listOf(theDto)) }
    }

    @Test
    fun findById() {
        every { repo.findById(1) } returns Optional.of(task)
        every { util.entityToDto(task) } returns theDto
        every { util.addSelfLink(theDto) } returns theDto

        val dto = service.findById(idTask = 1)

        Assertions.assertNotNull(dto)
        Assertions.assertEquals(theDto, dto)
        verify (exactly = 1) { repo.findById(1) }
        verify (exactly = 1) { util.entityToDto(task) }
        verify (exactly = 1) { util.addSelfLink(theDto) }
    }

    @Test
    fun delete() {
        every { repo.findById(1) } returns Optional.of(task)

        service.delete(idTask = 1)

        verify (exactly = 1) { repo.findById(1) }
        verify (exactly = 1) { repo.delete(task) }
    }


    private fun createEntity(
        l: Long,
        s: String,
        s1: String,
        b: Boolean,
        of: LocalDate?,
        of1: LocalTime?,
        of2: LocalDate?,
        of3: LocalTime?,
        s2: String,
        i: Long
    ): Task {
        return Task(l, s, s1, b, of!!, of1!!, of2!!, of3!!, s2, i)
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