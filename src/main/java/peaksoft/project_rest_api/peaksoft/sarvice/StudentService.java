package peaksoft.project_rest_api.peaksoft.sarvice;

import peaksoft.project_rest_api.peaksoft.dto.student.StudentRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.student.StudentResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;

import java.util.List;

public interface StudentService {

    Response saveStudent(StudentRequestDto student);

    StudentResponseDto findByStudentId(Long id);

    Response deleteByStudentId(Long id);

    Response updateByStudentId(Long id, StudentRequestDto student);

    List<StudentResponseDto> getAllStudent();
}
