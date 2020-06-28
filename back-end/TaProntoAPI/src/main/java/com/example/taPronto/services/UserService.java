package com.example.taPronto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.taPronto.model.User;
import com.example.taPronto.repositories.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findUser(Integer id) throws ObjectNotFoundException{
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id:" + id));
	}
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	public User create(User user) {
		return userRepository.save(user);
	}
	public ResponseEntity<?> deleteUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			userRepository.delete(user.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	public ResponseEntity<User> editUser(Integer id, User userEdit) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			userEdit.setId(user.get().getId());
			return new ResponseEntity<User>(userRepository.save(userEdit), HttpStatus.OK);
		}
	}
}
