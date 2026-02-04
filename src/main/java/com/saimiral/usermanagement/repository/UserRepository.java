package com.saimiral.usermanagement.repository;

import com.saimiral.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByAgeBetween(Integer minAge, Integer maxAge, Pageable pageable);

}
