package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.project_rest_api.peaksoft.dto.student.StudentRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.student.StudentResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.StudentService;
import java.util.List;

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
}
