package com.gabsiree.clyde.domain.repository;

import com.gabsiree.clyde.domain.model.Classroom;
import com.gabsiree.clyde.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByTeacher(User teacher);
    Optional<Classroom> findByCode(String code);
}
