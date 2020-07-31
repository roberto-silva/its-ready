package com.roberto.taPronto.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.roberto.taPronto.dto.UserDTO;
import com.roberto.taPronto.model.User;
import com.roberto.taPronto.services.UserService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService service;

//	Get All usando DTO
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = this.service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

//	Endpoint Paginado
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<UserDTO>> findPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<User> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<UserDTO> listDTO = list.map(obj -> new UserDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id) throws ObjectNotFoundException {
		User obj = this.service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody @Valid  UserDTO objDto) {
		User obj = service.insert(service.fromDTO(objDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody @Valid UserDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = this.service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> update(@PathVariable Integer id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
