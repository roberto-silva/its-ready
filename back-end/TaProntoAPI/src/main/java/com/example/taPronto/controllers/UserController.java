package com.example.taPronto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.taPronto.model.User;
import com.example.taPronto.services.UserService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(){
		List<User> usersList = this.userService.findAllUsers();
		if(usersList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/view/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneUser(@PathVariable(value="id") Integer id) throws ObjectNotFoundException{
		User user = this.userService.findUser(id);
		return ResponseEntity.ok().body(user);
		
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        User newUser = this.userService.create(user);
        return ResponseEntity.ok().body(newUser);
    }
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editUser(@PathVariable(value="id") Integer id, 
			@RequestBody @Validated User user){
		return this.userService.editUser(id, user);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value="id") Integer id){
		return this.userService.deleteUser(id);
	}

}
