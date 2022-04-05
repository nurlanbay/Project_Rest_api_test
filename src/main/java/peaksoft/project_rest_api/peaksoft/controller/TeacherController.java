package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_tutorial.peaksoft.dto.teacher.TeacherRequestDto;
import rest_tutorial.peaksoft.dto.teacher.TeacherResponseDto;
import rest_tutorial.peaksoft.exception.BadRequestException;
import rest_tutorial.peaksoft.exception.CompanyNotFoundException;
import rest_tutorial.peaksoft.response.Response;
import rest_tutorial.peaksoft.sarvice.TeacherService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherResponseDto> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @PostMapping("/save")
    public Response saveTeacher(@RequestBody TeacherRequestDto teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @GetMapping("/get/{teacherId}")
    public TeacherResponseDto findById(@PathVariable("teacherId")Long teacherId) {
        return teacherService.findByTeacherId(teacherId);
    }

    @DeleteMapping("/delete/{teacherId}")
    public Response deleteById(@PathVariable("teacherId") Long teacherId) {
        return teacherService.deleteByTeacherId(teacherId);
    }

    @PutMapping("/update/{teacherId}")
    public Response updateById(@PathVariable Long teacherId,
                               @RequestBody TeacherRequestDto teacher) {
        return teacherService.updateByTeacherId(teacherId, teacher);
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
