package com.roberto.taPronto.service;

import com.roberto.taPronto.domain.Budget;
import com.roberto.taPronto.domain.Task;
import com.roberto.taPronto.dto.TaskDTO;
import com.roberto.taPronto.repository.TaskRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository repository;

    public Task findById(Integer id) throws ObjectNotFoundException {

        Optional<Task> Service = repository.findById(id);
        return Service.orElseThrow(() -> new ObjectNotFoundException("Service not found!"));
    }

    public Page<Task> findAll(Pageable pageable, String search) {
        return repository.findAllTaskByCollaborator_NameContainsIgnoreCaseOrBudget_Client_NameContainsIgnoreCase(search, search, pageable);
    }

    public Task create(Budget budget) {
        Task newTask = new Task();
        newTask.setBudget(budget);

        return repository.save(newTask);
    }

    public Task update(TaskDTO ServiceDTO, Integer id) throws ObjectNotFoundException {
        Task currentTask = this.findById(id);
        BeanUtils.copyProperties(ServiceDTO, currentTask, "approval");
        return this.repository.save(currentTask);
    }

}
