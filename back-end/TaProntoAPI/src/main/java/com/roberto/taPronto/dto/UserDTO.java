package com.roberto.taPronto.dto;

import java.io.Serializable;

import com.roberto.taPronto.model.User;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String cpf;

	private String role;

	private String phone;

	public UserDTO() {
		super();
	}

	public UserDTO(int id, String name, String cpf, String role, String phone) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.role = role;
		this.phone = phone;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.cpf = user.getCpf();
		this.role = user.getRole();
		this.phone = user.getPhone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
