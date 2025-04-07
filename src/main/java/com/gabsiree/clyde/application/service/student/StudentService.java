package com.gabsiree.clyde.application.service.student;

import com.gabsiree.clyde.domain.dto.ClassroomDTO;
import com.gabsiree.clyde.domain.dto.JoinClassroomRequest;
import com.gabsiree.clyde.domain.model.Classroom;
import com.gabsiree.clyde.domain.model.User;
import com.gabsiree.clyde.domain.repository.authentication.UserRepository;
import com.gabsiree.clyde.domain.repository.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public List<ClassroomDTO> listEnrolledClassrooms() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Classroom> classrooms = studentRepository.findByStudentsContaining(student);

        return classrooms.stream()
                .map(classroom -> ClassroomDTO.builder()
                        .id(classroom.getId())
                        .name(classroom.getName())
                        .code(classroom.getCode())
                        .teacher(classroom.getTeacher().getName())
                        .build())
                .toList();
    }

    public void joinClassroom(JoinClassroomRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Classroom classroom = studentRepository.findByCode(request.getCode())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        if (classroom.getStudents().contains(student)) {
            throw new RuntimeException("User is already enrolled in this classroom");
        }

        classroom.getStudents().add(student);
        studentRepository.save(classroom);
    }
}
