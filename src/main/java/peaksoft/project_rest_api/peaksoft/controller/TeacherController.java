package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.project_rest_api.peaksoft.dto.teacher.TeacherRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.teacher.TeacherResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.TeacherService;
import java.util.List;

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
}
