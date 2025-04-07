package com.gabsiree.clyde.domain.repository.student;

import com.gabsiree.clyde.domain.model.Classroom;
import com.gabsiree.clyde.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByCode(String code);
    List<Classroom> findByStudentsContaining(User student);
}
