package com.roberto.taPronto.dto;


import com.roberto.taPronto.domain.Budget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    private Integer id;

    @NotNull(message = "Client is mandatory.")
    private Long clientId;

    @NotNull(message = "Collaborator is mandatory.")
    private Long collaboratorId;

    @NotNull(message = "Budget date is mandatory.")
    private Instant budgetDate;

    @Builder.Default
    private Boolean approval = false;

    public BudgetDTO(Budget budget) {
        this.id = budget.getId();
        this.clientId = budget.getClient().getId();
        this.collaboratorId = budget.getCollaborator().getId();
        this.budgetDate = budget.getBudgetDate();
        this.approval = budget.getApproval();
    }
}
