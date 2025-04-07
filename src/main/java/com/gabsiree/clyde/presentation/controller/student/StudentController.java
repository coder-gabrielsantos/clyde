package com.gabsiree.clyde.presentation.controller.student;

import com.gabsiree.clyde.application.service.student.StudentService;
import com.gabsiree.clyde.domain.dto.ClassroomDTO;
import com.gabsiree.clyde.domain.dto.JoinClassroomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student/classrooms")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<ClassroomDTO>> listEnrolledClassrooms() {
        return ResponseEntity.ok(studentService.listEnrolledClassrooms());
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinClassroom(@RequestBody JoinClassroomRequest request) {
        studentService.joinClassroom(request);
        return ResponseEntity.ok("Successfully joined classroom");
    }
}
