package com.roberto.taPronto.controller;

import com.roberto.taPronto.domain.User;
import com.roberto.taPronto.domain.enums.Role;
import com.roberto.taPronto.dto.UserDTO;
import com.roberto.taPronto.service.UserService;
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
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private UserService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ATTENDANT')")
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable,
                                                 @RequestParam String search) {
        return ResponseEntity.ok().body(this.service.findAll(pageable, search).map(UserDTO::new));
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAnyRole('ADMIN', 'ATTENDANT')")
    public ResponseEntity<Set<UserDTO>> findAll(@RequestParam Role role) {
        return ResponseEntity.ok().body(this.service.findAllUsersByRole(role).stream()
                .map(UserDTO::new).collect(Collectors.toSet()));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('COSTUMER')")
    public ResponseEntity<User> findById(@PathVariable Integer id) throws ObjectNotFoundException {
        User obj = this.service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> insert(@RequestBody @Valid UserDTO objDto) {
        User obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException {
        var user = this.service.update(objDto, id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
