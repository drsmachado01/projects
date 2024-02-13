package br.com.darlan.tasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "TB_TASKS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTask;
    String taskName;
    String taskDescription;
    Boolean uniqueExecution;
    LocalDate startDate;
    LocalTime startTime;
    LocalDate endDate;
    LocalTime endTime;
    String address;
    String host;
}
