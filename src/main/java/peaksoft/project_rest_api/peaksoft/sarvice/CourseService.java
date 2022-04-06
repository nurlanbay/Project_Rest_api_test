package peaksoft.project_rest_api.peaksoft.sarvice;


import peaksoft.project_rest_api.peaksoft.dto.course.CourseRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.course.CourseResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;

import java.util.List;

public interface CourseService {

    Response saveCourse(CourseRequestDto newCourse);

    CourseResponseDto findByCourseId(Long id);

    Response deleteByIdCourse(Long id);

    Response updateById(Long id, CourseRequestDto newCourse);

    List<CourseResponseDto> getAllCourse();
}
