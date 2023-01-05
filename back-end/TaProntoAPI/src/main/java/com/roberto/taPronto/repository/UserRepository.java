package com.roberto.taPronto.repository;

import com.roberto.taPronto.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    boolean existsByCpf(String cpf);

    Page<User> findAllUserByNameContainsIgnoreCase(Pageable pageable, String search);
}
