package com.roberto.taPronto.dto;


import com.roberto.taPronto.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

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
        this.budget = new BudgetDTO(task.getBudget());
        this.status = task.getStatus().getCod();

        if(Objects.nonNull(task.getCollaborator())){
            this.collaborator = new UserDTO(task.getCollaborator());
        }

        if(Objects.nonNull(task.getInitialServiceDate())){
            this.initialServiceDate = task.getInitialServiceDate();
        }

    }
}
