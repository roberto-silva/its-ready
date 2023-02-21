package com.roberto.taPronto.dto;


import com.roberto.taPronto.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Integer id;

    private UserDTO collaborator;

    private Instant initialServiceDate;

    private BudgetDTO budget;

    @Builder.Default
    private Integer status = 0;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.collaborator = new UserDTO(task.getCollaborator());
        this.initialServiceDate = task.getInitialServiceDate();
        this.budget = new BudgetDTO(task.getBudget());
        this.status = task.getStatus().getCod();
    }
}
