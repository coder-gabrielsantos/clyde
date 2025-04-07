package com.gabsiree.clyde.domain.repository;

import com.gabsiree.clyde.domain.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByCode(String code);
}
