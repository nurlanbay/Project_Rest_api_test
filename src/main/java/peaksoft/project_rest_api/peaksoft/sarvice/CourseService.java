package peaksoft.project_rest_api.peaksoft.sarvice;


import rest_tutorial.peaksoft.dto.course.CourseRequestDto;
import rest_tutorial.peaksoft.dto.course.CourseResponseDto;
import rest_tutorial.peaksoft.response.Response;

import java.util.List;

public interface CourseService {

    Response saveCourse(CourseRequestDto newCourse);

    CourseResponseDto findByCourseId(Long id);

    Response deleteByIdCourse(Long id);

    Response updateById(Long id, CourseRequestDto newCourse);

    List<CourseResponseDto> getAllCourse();
}
