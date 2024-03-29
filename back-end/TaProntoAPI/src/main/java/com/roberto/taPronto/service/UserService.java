package com.roberto.taPronto.service;

import com.roberto.taPronto.domain.Address;
import com.roberto.taPronto.domain.User;
import com.roberto.taPronto.domain.enums.Role;
import com.roberto.taPronto.dto.UserDTO;
import com.roberto.taPronto.repository.UserRepository;
import com.roberto.taPronto.repository.projection.SimplifieldUserProjection;
import com.roberto.taPronto.security.UserSpringSecurity;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    public User findById(Long id) throws ObjectNotFoundException {

        Optional<User> user = repository.findById(Long.valueOf(id));
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found!"));
    }

    public boolean existsByCPF(String cpf) {
        return repository.existsByCpf(cpf);
    }

    public Page<User> findAll(Pageable pageable, String search) {
        return repository.findAllUserByNameContainsIgnoreCase(pageable, search);
    }

    public User create(UserDTO userDTO) {
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);
        newUser.setAddress(new Address(userDTO.getAddress()));
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.addProfile(Role.COSTUMER);
        return repository.save(newUser);
    }

    public User update(UserDTO userDTO, Long id) throws ObjectNotFoundException {

        User currentUser = this.findById(id);
        BeanUtils.copyProperties(userDTO, currentUser);
        currentUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return this.repository.save(currentUser);
    }

    public void delete(Long id) throws ObjectNotFoundException {

        this.repository.delete(this.findById(id));
    }

    public static UserSpringSecurity getUserLogged() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public Set<SimplifieldUserProjection> findAllUsersByRole(Role role) {
        return repository.findAllUserByProfileContains(role.getCod());
    }
}
