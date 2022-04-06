package peaksoft.project_rest_api.peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.project_rest_api.peaksoft.dto.course.CourseRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.course.CourseResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.CourseService;


import java.util.List;


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

}
