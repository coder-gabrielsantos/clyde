package com.gabsiree.clyde.domain.repository.authentication;

import com.gabsiree.clyde.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
