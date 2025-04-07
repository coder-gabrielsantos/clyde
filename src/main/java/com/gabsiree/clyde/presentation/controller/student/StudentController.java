package com.gabsiree.clyde.presentation.controller.student;

import com.gabsiree.clyde.application.service.student.StudentService;
import com.gabsiree.clyde.domain.dto.JoinClassroomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/classrooms")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService classroomService;

    @PostMapping("/join")
    public ResponseEntity<?> joinClassroom(@RequestBody JoinClassroomRequest request) {
        classroomService.joinClassroom(request);

        return ResponseEntity.ok("Successfully joined classroom");
    }
}
