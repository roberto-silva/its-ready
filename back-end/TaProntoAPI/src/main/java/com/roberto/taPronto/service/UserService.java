package com.roberto.taPronto.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.roberto.taPronto.dto.UserDTO;
import com.roberto.taPronto.domain.User;
import com.roberto.taPronto.repository.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository repository;

	public User findById(Integer id) throws ObjectNotFoundException {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! Id:" + id));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User create(UserDTO user) {
		return repository.save(new User(user));
	}

	public User update(User obj, Integer id) throws ObjectNotFoundException {
		User currentUser = this.findById(id);

		currentUser.setName(obj.getName());
		currentUser.setPhone(obj.getPhone());
		currentUser.setAddress(obj.getAddress());
		currentUser.setCpf(obj.getCpf());
		currentUser.setRole(obj.getRole());

		return this.repository.save(currentUser);
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		this.repository.delete(this.findById(id));
	}

}
