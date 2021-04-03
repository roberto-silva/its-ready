package com.roberto.taPronto.controller;

import com.roberto.taPronto.dto.UserDTO;
import com.roberto.taPronto.model.User;
import com.roberto.taPronto.service.UserService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService service;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = this.service.findAll();
		List<UserDTO> listDTO = list.stream().map(UserDTO:: new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}


	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<UserDTO>> findPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<User> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<UserDTO> listDTO = list.map(UserDTO:: new);
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Integer id) throws ObjectNotFoundException {
		User obj = this.service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody @Valid  UserDTO objDto) {
		User obj = service.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException {
		User user = new User(objDto);

		user = this.service.update(user, id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> update(@PathVariable Integer id) throws ObjectNotFoundException {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
