package com.examgrade.service.impl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examgrade.dto.TeacherRequest;
import com.examgrade.dto.TeacherResponse;
import com.examgrade.entity.Role;
import com.examgrade.entity.User;
import com.examgrade.repository.UserRepository;
import com.examgrade.service.TeacherService;

 

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ================= CREATE TEACHER =================
    @Override
    public TeacherResponse createTeacher(TeacherRequest request) {

        // Check duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User teacher = new User();
        teacher.setName(request.getName());
        teacher.setEmail(request.getEmail());

        // Use provided password or default password
        String rawPassword = (request.getPassword() == null || request.getPassword().isBlank())
                ? "Temp@1234"
                : request.getPassword();

        teacher.setPassword(passwordEncoder.encode(rawPassword));
        teacher.setRole(Role.ROLE_TEACHER);
        teacher.setEnabled(true);
        teacher.setCreatedAt(LocalDateTime.now());

        // Teacher-specific fields
        teacher.setSubject(request.getSubject());
        teacher.setPhone(request.getPhone());
        teacher.setDepartment(request.getDepartment());

        // Save to database
        User savedTeacher = userRepository.save(teacher);

        return convertToResponse(savedTeacher);
    }

    // ================= GET ALL TEACHERS =================
    @Override
    public List<TeacherResponse> getAllTeachers() {
        List<User> teachers = userRepository.findByRole(Role.ROLE_TEACHER);

        return teachers.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // ================= GET TEACHER BY ID =================
    @Override
    public TeacherResponse getTeacherById(Long id) {
        User teacher = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getRole() != Role.ROLE_TEACHER) {
            throw new RuntimeException("User is not a teacher");
        }

        return convertToResponse(teacher);
    }

    // ================= UPDATE TEACHER =================
    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {

        User teacher = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getRole() != Role.ROLE_TEACHER) {
            throw new RuntimeException("User is not a teacher");
        }

        // Update fields
        teacher.setName(request.getName());
        teacher.setEmail(request.getEmail());
        teacher.setSubject(request.getSubject());
        teacher.setPhone(request.getPhone());
        teacher.setDepartment(request.getDepartment());

        // Update password only if provided
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            teacher.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updatedTeacher = userRepository.save(teacher);

        return convertToResponse(updatedTeacher);
    }

    // ================= DELETE TEACHER =================
    @Override
    public void deleteTeacher(Long id) {
        User teacher = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getRole() != Role.ROLE_TEACHER) {
            throw new RuntimeException("User is not a teacher");
        }

        userRepository.delete(teacher);
    }

    // ================= ENABLE / DISABLE =================
    @Override
    public TeacherResponse toggleTeacherStatus(Long id) {

        User teacher = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getRole() != Role.ROLE_TEACHER) {
            throw new RuntimeException("User is not a teacher");
        }

        teacher.setEnabled(!teacher.getEnabled());

        User updatedTeacher = userRepository.save(teacher);

        return convertToResponse(updatedTeacher);
    }

    // ================= CONVERT ENTITY TO DTO =================
    private TeacherResponse convertToResponse(User teacher) {

        TeacherResponse response = new TeacherResponse();

        response.setId(teacher.getId());
        response.setName(teacher.getName());
        response.setEmail(teacher.getEmail());
        response.setSubject(teacher.getSubject());
        response.setPhone(teacher.getPhone());
        response.setDepartment(teacher.getDepartment());
        response.setEnabled(teacher.getEnabled());

        return response;
    }
}
