package com.roberto.taPronto.controller;

import com.roberto.taPronto.domain.Budget;
import com.roberto.taPronto.dto.BudgetDTO;
import com.roberto.taPronto.dto.SimplifieldBudgetDTO;
import com.roberto.taPronto.service.BudgetService;
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
@RequestMapping(value = "/api/v1/budgets")
public class BudgeController {

    private BudgetService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ATTENDANT')")
    public ResponseEntity<Page<BudgetDTO>> findAll(Pageable pageable,
                                                   @RequestParam String search) {
        return ResponseEntity.ok().body(this.service.findAll(pageable, search).map(BudgetDTO::new));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('COSTUMER')")
    public ResponseEntity<Budget> findById(@PathVariable Integer id) throws ObjectNotFoundException {
        Budget obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> insert(@RequestBody @Valid SimplifieldBudgetDTO objDto) throws ObjectNotFoundException {
        Budget obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<BudgetDTO> update(@RequestBody @Valid SimplifieldBudgetDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException {
        var budget = this.service.update(objDto, id);
        return ResponseEntity.ok().body(new BudgetDTO(budget));
    }

}
