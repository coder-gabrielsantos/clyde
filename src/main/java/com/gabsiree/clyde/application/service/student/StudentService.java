package com.gabsiree.clyde.application.service.student;

import com.gabsiree.clyde.domain.dto.JoinClassroomRequest;
import com.gabsiree.clyde.domain.model.Classroom;
import com.gabsiree.clyde.domain.model.User;
import com.gabsiree.clyde.domain.repository.ClassroomRepository;
import com.gabsiree.clyde.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;

    public void joinClassroom(JoinClassroomRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Classroom classroom = classroomRepository.findByCode(request.getCode())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        if (classroom.getStudents().contains(student)) {
            throw new RuntimeException("User is already enrolled in this classroom.");
        }

        classroom.getStudents().add(student);
        classroomRepository.save(classroom);
    }
}
