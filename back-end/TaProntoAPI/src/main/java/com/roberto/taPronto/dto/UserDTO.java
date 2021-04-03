package com.roberto.taPronto.dto;

import com.roberto.taPronto.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Integer id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String cpf;
	@NotEmpty
	private String role;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
	@NotNull
	private AddressDTO address;

	public UserDTO(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.cpf = user.getCpf();
		this.role = user.getRole();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.address = new AddressDTO(user.getAddress());
	}


}
