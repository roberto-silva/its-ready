package com.roberto.taPronto.service;

import com.roberto.taPronto.domain.Budget;
import com.roberto.taPronto.domain.User;
import com.roberto.taPronto.dto.BudgetDTO;
import com.roberto.taPronto.dto.SimplifieldBudgetDTO;
import com.roberto.taPronto.dto.TaskDTO;
import com.roberto.taPronto.repository.BudgetRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BudgetService {

    private BudgetRepository repository;
    private TaskService taskService;
    private UserService userService;

    public Budget findById(Integer id) throws ObjectNotFoundException {

        Optional<Budget> Budget = repository.findById(id);
        return Budget.orElseThrow(() -> new ObjectNotFoundException("Budget not found!"));
    }

    public Page<Budget> findAll(Pageable pageable, String search) {
        return repository.findAllBudgetByCollaborator_NameContainsIgnoreCaseOrClient_NameContainsIgnoreCase(search, search, pageable);
    }

    public Budget create(SimplifieldBudgetDTO budgetDTO) throws ObjectNotFoundException {
        Budget newBudget = new Budget();
        BeanUtils.copyProperties(budgetDTO, newBudget, "approval");
        User client = userService.findById(budgetDTO.getClientId());
        User collaborator = userService.findById(budgetDTO.getCollaboratorId());
        newBudget.setClient(client);
        newBudget.setCollaborator(collaborator);
        return repository.save(newBudget);
    }

    public Budget update(SimplifieldBudgetDTO budgetDTO, Integer id) throws ObjectNotFoundException {
        Budget currentBudget = this.findById(id);
        BeanUtils.copyProperties(budgetDTO, currentBudget, "approval");
        User client = userService.findById(budgetDTO.getClientId());
        User collaborator = userService.findById(budgetDTO.getCollaboratorId());
        currentBudget.setClient(client);
        currentBudget.setCollaborator(collaborator);
        currentBudget.setApproval(budgetDTO.getApproval());
        currentBudget = this.repository.save(currentBudget);

        if (currentBudget.getApproval()) {
            taskService.create(currentBudget);
        }

        return currentBudget;
    }

}
