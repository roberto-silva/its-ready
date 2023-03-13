package com.roberto.taPronto.dto;

import com.roberto.taPronto.domain.User;
import com.roberto.taPronto.domain.enums.Role;
import com.roberto.taPronto.repository.projection.SimplifieldUserProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "The field is mandatory.")
    @Size(min = 2, max = 100, message = "User name must be between 2 and 100 characters long.")
    private String name;

    @NotEmpty(message = "The field is mandatory.")
    @CPF(message = "Invalid cpf format.")
    private String cpf;

    @NotEmpty(message = "The field is mandatory.")
    private Set<Integer> profile = new HashSet<>();

    @NotEmpty(message = "The field is mandatory.")
    private String phone;

    @NotEmpty(message = "The field is mandatory.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "The field is mandatory.")
    @Valid
    private AddressDTO address;

    @NotEmpty(message = "The field is mandatory.")
    private String password;

    private boolean activated;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.profile = user.getProfiles().stream().map(Role::getCod).collect(Collectors.toSet());
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.address = new AddressDTO(user.getAddress());
        this.activated = user.isActivated();
    }

	public UserDTO(SimplifieldUserProjection userProjection) {
		this.id = userProjection.getId();
		this.name = userProjection.getName();
	}
}
