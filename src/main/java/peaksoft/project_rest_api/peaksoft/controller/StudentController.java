package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_tutorial.peaksoft.dto.student.StudentRequestDto;
import rest_tutorial.peaksoft.dto.student.StudentResponseDto;
import rest_tutorial.peaksoft.exception.BadRequestException;
import rest_tutorial.peaksoft.exception.CompanyNotFoundException;
import rest_tutorial.peaksoft.response.Response;
import rest_tutorial.peaksoft.sarvice.StudentService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponseDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PostMapping("/save")
    public Response saveStudent(@RequestBody StudentRequestDto student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/get/{studentId}")
    public StudentResponseDto findById(@PathVariable("studentId")Long studentId) {
        return studentService.findByStudentId(studentId);
    }

    @DeleteMapping("/delete/{studentId}")
    public Response deleteById(@PathVariable("studentId") Long studentId) {
        return studentService.deleteByStudentId(studentId);
    }

    @PutMapping("/update/{studentId}")
    public Response updateById(@PathVariable Long studentId,
                               @RequestBody StudentRequestDto student) {
        return studentService.updateByStudentId(studentId, student);
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
