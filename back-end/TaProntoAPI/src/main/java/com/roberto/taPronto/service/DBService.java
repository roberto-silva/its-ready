package com.roberto.taPronto.service;

import com.roberto.taPronto.domain.enums.Profile;
import com.roberto.taPronto.dto.AddressDTO;
import com.roberto.taPronto.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class DBService {

    private UserService userService;

    public void init(){
        this.createUsersDefault();
    }

    private void createUsersDefault(){
        Set<UserDTO> dtoSet = new HashSet<>();
        dtoSet.add(createUserAdmin());
        dtoSet.forEach(userDTO -> {
            if(!userService.existsByCPF(userDTO.getCpf())){
                userService.create(userDTO);
            }
        });
    }

    private UserDTO createUserAdmin() {
        var adminProfile = new HashSet<Integer>();
        adminProfile.add(Profile.ADMIN.getCod());
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
