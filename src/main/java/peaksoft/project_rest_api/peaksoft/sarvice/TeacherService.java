package peaksoft.project_rest_api.peaksoft.sarvice;

import rest_tutorial.peaksoft.dto.teacher.TeacherRequestDto;
import rest_tutorial.peaksoft.dto.teacher.TeacherResponseDto;
import rest_tutorial.peaksoft.response.Response;

import java.util.List;

public interface TeacherService {

    Response saveTeacher(TeacherRequestDto teacher);

    TeacherResponseDto findByTeacherId(Long id);

    Response deleteByTeacherId(Long id);

    Response updateByTeacherId(Long id, TeacherRequestDto teacher);

    List<TeacherResponseDto> getAllTeacher();
}
