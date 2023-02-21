package com.roberto.taPronto.dto;


import com.roberto.taPronto.domain.Budget;
import com.roberto.taPronto.domain.User;
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
    private User client;

    @NotNull(message = "Collaborator is mandatory.")
    private User collaborator;

    @NotNull(message = "Budget date is mandatory.")
    private Instant budgetDate;

    @Builder.Default
    private Boolean approval = false;

    private Double amount;

    public BudgetDTO(Budget budget) {
        this.id = budget.getId();
        this.client = budget.getClient();
        this.collaborator = budget.getCollaborator();
        this.budgetDate = budget.getBudgetDate();
        this.approval = budget.getApproval();
        this.amount = budget.getAmount();
    }
}
