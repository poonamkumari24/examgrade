package com.examgrade.service;
import java.util.List;
import com.examgrade.dto.TeacherRequest;
import com.examgrade.dto.TeacherResponse;

public interface TeacherService {

    TeacherResponse createTeacher(TeacherRequest request);

    List<TeacherResponse> getAllTeachers();

    TeacherResponse getTeacherById(Long id);

    TeacherResponse updateTeacher(Long id, TeacherRequest request);

    void deleteTeacher(Long id);

    TeacherResponse toggleTeacherStatus(Long id);
}
