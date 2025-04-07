package com.gabsiree.clyde.presentation.controller.teacher;

import com.gabsiree.clyde.application.service.teacher.TeacherService;
import com.gabsiree.clyde.domain.dto.ClassroomDTO;
import com.gabsiree.clyde.domain.dto.CreateClassroomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacher/classrooms")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService classroomService;

    @PostMapping("new")
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody CreateClassroomRequest request) {
        ClassroomDTO dto = classroomService.createClassroom(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/own")
    public ResponseEntity<List<ClassroomDTO>> listOwnedClassrooms() {
        List<ClassroomDTO> classrooms = classroomService.listOwnedClassrooms();
        return ResponseEntity.ok(classrooms);
    }
}
