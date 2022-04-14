package com.roberto.taPronto.dto;

import com.roberto.taPronto.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Integer id;

	@NotEmpty
	@Size(min = 2, max = 100, message = "User name must be between 2 and 100 characters long.")
	private String name;

	@NotEmpty
	@CPF(message = "Invalid cpf format.")
	private String cpf;

	@NotEmpty
	private String role;

	@NotEmpty
	private String phone;

	@NotEmpty
	@Email(message = "Invalid email format.")
	private String email;

	@NotNull
	@Valid
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
