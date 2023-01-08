package com.roberto.taPronto.controller;

import com.roberto.taPronto.domain.Task;
import com.roberto.taPronto.dto.TaskDTO;
import com.roberto.taPronto.service.TaskService;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/task")
public class TaskController {

    private TaskService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ATTENDANT')")
    public ResponseEntity<Page<TaskDTO>> findAll(Pageable pageable,
                                                   @RequestParam String search) {
        return ResponseEntity.ok().body(this.service.findAll(pageable, search).map(TaskDTO::new));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('COSTUMER')")
    public ResponseEntity<Task> findById(@PathVariable Integer id) throws ObjectNotFoundException {
        Task obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> insert(@RequestBody @Valid TaskDTO objDto) {
        Task obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TaskDTO> update(@RequestBody @Valid TaskDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException {
        var task = this.service.update(objDto, id);
        return ResponseEntity.ok().body(new TaskDTO(task));
    }

}
