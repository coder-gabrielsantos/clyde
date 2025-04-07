package com.gabsiree.clyde.presentation.controller;

import com.gabsiree.clyde.application.service.ClassroomService;
import com.gabsiree.clyde.domain.dto.ClassroomDTO;
import com.gabsiree.clyde.domain.dto.CreateClassroomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<ClassroomDTO> create(@RequestBody CreateClassroomRequest request) {
        ClassroomDTO dto = classroomService.createClassroom(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/owned")
    public ResponseEntity<List<ClassroomDTO>> listOwned() {
        List<ClassroomDTO> classrooms = classroomService.listOwnedClassrooms();
        return ResponseEntity.ok(classrooms);
    }
}
