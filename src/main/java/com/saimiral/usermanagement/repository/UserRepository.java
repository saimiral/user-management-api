package com.saimiral.usermanagement.repository;

import com.saimiral.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Page<User> findByAgeBetween(Integer minAge, Integer maxAge, Pageable pageable);
    Optional<User> findByEmail (String email);

}
