package com.examgrade.controller;

import com.examgrade.dto.TeacherRequest;
import com.examgrade.dto.TeacherResponse;
import com.examgrade.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/teachers")
@CrossOrigin(origins = "*")
public class AdminTeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * Create a new teacher account
     * POST /api/admin/teachers
     */
    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(
            @Valid @RequestBody TeacherRequest request) {

        TeacherResponse response = teacherService.createTeacher(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all teachers
     * GET /api/admin/teachers
     */
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    /**
     * Get one teacher by ID
     * GET /api/admin/teachers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(
            @PathVariable Long id) {

        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    /**
     * Update teacher details
     * PUT /api/admin/teachers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequest request) {

        return ResponseEntity.ok(
                teacherService.updateTeacher(id, request)
        );
    }

    /**
     * Delete teacher
     * DELETE /api/admin/teachers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(
            @PathVariable Long id) {

        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }

    /**
     * Enable/Disable teacher account
     * PATCH /api/admin/teachers/{id}/toggle-status
     */
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<TeacherResponse> toggleTeacherStatus(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                teacherService.toggleTeacherStatus(id)
        );
    }
}
