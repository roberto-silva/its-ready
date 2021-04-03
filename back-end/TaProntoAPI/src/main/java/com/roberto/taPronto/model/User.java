package com.roberto.taPronto.model;

import com.roberto.taPronto.dto.AddressDTO;
import com.roberto.taPronto.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="user_account",uniqueConstraints = {
		@UniqueConstraint(columnNames={"cpf"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="role")
	private String role;
	
	@Column(name="phone")
	private String phone;

	@Column(name="email")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public User(UserDTO objDto) {
		this.id = objDto.getId();
		this.name = objDto.getName();
		this.cpf = objDto.getCpf();
		this.role = objDto.getRole();
		this.phone = objDto.getPhone();
		this.email = objDto.getEmail();
		this.address = new Address(objDto.getAddress());
	}
}
