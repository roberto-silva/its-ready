package com.roberto.taPronto.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.roberto.taPronto.dto.UserDTO;
import com.roberto.taPronto.model.User;
import com.roberto.taPronto.repositories.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User find(Integer id) throws ObjectNotFoundException {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id:" + id));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User insert(User user) {
		return repository.save(user);
	}

	public ResponseEntity<?> delete(Integer id) {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			repository.delete(user.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	public User update(User obj) throws ObjectNotFoundException {
		User newObj = this.find(obj.getId());
		updateData(newObj, obj);
		return this.repository.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		if(!obj.getName().isEmpty()) {
			newObj.setName(obj.getName());
		}
		if(!obj.getPhone().isEmpty()) {
			newObj.setPhone(obj.getPhone());
		}
		if(!obj.getRole().isEmpty()) {
			newObj.setRole(obj.getRole());
		}
	}

	public User fromDTO(@Valid UserDTO objDto) {
		User user = new User(objDto.getId(), objDto.getName(), objDto.getCpf(), objDto.getRole(), objDto.getPhone());
		return user;
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

}
