package com.gabsiree.clyde.application.service.teacher;

import com.gabsiree.clyde.domain.dto.ClassroomDTO;
import com.gabsiree.clyde.domain.dto.CreateClassroomRequest;
import com.gabsiree.clyde.domain.model.Classroom;
import com.gabsiree.clyde.domain.model.User;
import com.gabsiree.clyde.domain.repository.ClassroomRepository;
import com.gabsiree.clyde.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;

    public ClassroomDTO createClassroom(CreateClassroomRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User teacher = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        String code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Classroom classroom = Classroom.builder()
                .name(request.getName())
                .code(code)
                .teacher(teacher)
                .build();

        Classroom saved = classroomRepository.save(classroom);

        return ClassroomDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .code(saved.getCode())
                .teacher(saved.getTeacher().getName())
                .build();
    }

    public List<ClassroomDTO> listOwnedClassrooms() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User teacher = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return classroomRepository.findByTeacher(teacher)
                .stream()
                .map(classroom -> ClassroomDTO.builder()
                        .id(classroom.getId())
                        .name(classroom.getName())
                        .code(classroom.getCode())
                        .teacher(teacher.getName())
                        .build())
                .toList();
    }
}
