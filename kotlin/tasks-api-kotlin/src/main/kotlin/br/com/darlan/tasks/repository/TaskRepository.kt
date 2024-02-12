package br.com.darlan.tasks.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.scheduling.config.Task
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, Long> {
}