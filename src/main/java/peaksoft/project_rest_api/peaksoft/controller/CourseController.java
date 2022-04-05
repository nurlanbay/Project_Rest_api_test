package peaksoft.project_rest_api.peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_tutorial.peaksoft.dto.course.CourseRequestDto;
import rest_tutorial.peaksoft.dto.course.CourseResponseDto;
import rest_tutorial.peaksoft.exception.BadRequestException;
import rest_tutorial.peaksoft.exception.CompanyNotFoundException;
import rest_tutorial.peaksoft.response.Response;
import rest_tutorial.peaksoft.sarvice.CourseService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseResponseDto> getAllCourse() {
        return courseService.getAllCourse();
    }

    @PostMapping("/save")
    public Response saveCourse(@RequestBody CourseRequestDto newCourse) {
        return courseService.saveCourse(newCourse);
    }

    @GetMapping("/get/{courseId}")
    public CourseResponseDto findById(@PathVariable("courseId") Long courseId) {
        return courseService.findByCourseId(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public Response deleteById(@PathVariable("courseId") Long courseId) {
        return courseService.deleteByIdCourse(courseId);
    }

    @PutMapping("/update/{courseId}")
    public Response updateById(@PathVariable Long courseId,
                               @RequestBody CourseRequestDto course) {
        return courseService.updateById(courseId, course);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(CompanyNotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .massage(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .massage(badRequestException.getMessage())
                .build();
    }
}
