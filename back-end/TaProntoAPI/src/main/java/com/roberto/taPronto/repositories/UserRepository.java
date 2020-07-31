package com.roberto.taPronto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roberto.taPronto.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
