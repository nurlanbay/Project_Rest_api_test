package peaksoft.project_rest_api.peaksoft.sarvice;


import peaksoft.project_rest_api.peaksoft.dto.teacher.TeacherRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.teacher.TeacherResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;

import java.util.List;

public interface TeacherService {

    Response saveTeacher(TeacherRequestDto teacher);

    TeacherResponseDto findByTeacherId(Long id);

    Response deleteByTeacherId(Long id);

    Response updateByTeacherId(Long id, TeacherRequestDto teacher);

    List<TeacherResponseDto> getAllTeacher();
}
