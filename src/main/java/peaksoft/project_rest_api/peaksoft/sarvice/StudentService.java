package peaksoft.project_rest_api.peaksoft.sarvice;

import rest_tutorial.peaksoft.dto.student.StudentRequestDto;
import rest_tutorial.peaksoft.dto.student.StudentResponseDto;
import rest_tutorial.peaksoft.response.Response;

import java.util.List;

public interface StudentService {

    Response saveStudent(StudentRequestDto student);

    StudentResponseDto findByStudentId(Long id);

    Response deleteByStudentId(Long id);

    Response updateByStudentId(Long id, StudentRequestDto student);

    List<StudentResponseDto> getAllStudent();
}
