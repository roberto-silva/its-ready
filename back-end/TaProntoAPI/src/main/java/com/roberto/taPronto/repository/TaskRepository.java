package com.roberto.taPronto.repository;

import com.roberto.taPronto.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findAllTaskByCollaborator_NameContainsIgnoreCaseOrClient_NameContainsIgnoreCase(String collaboratorName,String clientName, Pageable pageable);
}
