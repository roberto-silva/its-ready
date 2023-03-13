package com.roberto.taPronto.repository;

import com.roberto.taPronto.domain.User;
import com.roberto.taPronto.repository.projection.SimplifieldUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByCpf(String cpf);

    Page<User> findAllUserByNameContainsIgnoreCase(Pageable pageable, String search);

    Set<SimplifieldUserProjection> findAllUserByProfileContains(Integer profile);
}
