package com.roberto.taPronto.service;

import com.roberto.taPronto.domain.enums.Role;
import com.roberto.taPronto.dto.AddressDTO;
import com.roberto.taPronto.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DBService {

    private UserService userService;

    public void init(){
        this.createUsersDefault();
    }

    private void createUsersDefault(){
        Set<UserDTO> userDTOS = new HashSet<>();
        userDTOS.add(createUserAdmin());
        userDTOS.forEach(userDTO -> {
            if(!userService.existsByCPF(userDTO.getCpf())){
                userService.create(userDTO);
            }
        });
    }

    private UserDTO createUserAdmin() {
        var adminProfile = new HashSet<Integer>();
        adminProfile.addAll(Arrays.stream(Role.values()).map(role -> role.getCod()).collect(Collectors.toSet()));
        return UserDTO.builder()
                .name("Admin")
                .cpf("546.480.050-66")
                .email("admin@gmail.com")
                .password("Admin@123")
                .phone("00 0000-0000")
                .profile(adminProfile)
                .activated(true)
                .address(buildAddressDTO())
                .build();
    }

    public AddressDTO buildAddressDTO(){
        return AddressDTO.builder().cep("58200000").city("GBA").district("New").referencePoint("Right there").street("nowhere").build();
    }

}
