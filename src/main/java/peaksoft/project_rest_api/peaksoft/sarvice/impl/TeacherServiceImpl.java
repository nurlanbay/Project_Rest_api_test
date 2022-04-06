package peaksoft.project_rest_api.peaksoft.sarvice.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.project_rest_api.peaksoft.dto.teacher.TeacherRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.teacher.TeacherResponseDto;
import peaksoft.project_rest_api.peaksoft.entity.Course;
import peaksoft.project_rest_api.peaksoft.entity.Teacher;
import peaksoft.project_rest_api.peaksoft.exception.BadRequestException;
import peaksoft.project_rest_api.peaksoft.exception.NotFoundException;
import peaksoft.project_rest_api.peaksoft.exception.TeacherNotFoundException;
import peaksoft.project_rest_api.peaksoft.repository.CourseRepo;
import peaksoft.project_rest_api.peaksoft.repository.TeacherRepo;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.TeacherService;


import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    // Dependency
    private final TeacherRepo teacherRepo;
    private final ModelMapper modelMapper;
    private final CourseRepo courseRepo;

    @Override
    public Response saveTeacher(TeacherRequestDto teacher) {
        String email = teacher.getEmail();

        checkTeacherEmail(email);
        Teacher teacher1 = modelMapper.map(teacher, Teacher.class);
        Course course  = courseRepo.findById(teacher.getCourseId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("company with id = %d does not exists", teacher.getCourseId())
                ));
        teacher1.setCourse(course);
        teacherRepo.save(teacher1);
        log.info("Student with email {} has successfully save to database"+teacher.getEmail());
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .massage(String.format("Student with email {} has successfully save to database"+teacher.getEmail()))
                .build();
    }
      public void checkTeacherEmail(String email){
          boolean exists = teacherRepo.existsByEmail(email);
          if (exists){
              log.info("Teacher with email {} already exists"+email);
              throw new BadRequestException("Teacher with email "+email+"already exits");
          }
      }

    @Override
    public TeacherResponseDto findByTeacherId(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(()->{
                    log.info("Teacher with id {] does not exists"+id);
                    throw new TeacherNotFoundException(String.format("Teacher with id %s does not exists"+id));
                });
          log.info("Found teacher with id {}"+id);
          return modelMapper.map(teacher,TeacherResponseDto.class);
    }

    @Override
    public Response deleteByTeacherId(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> {
                    log.info("Teacher with id {} does not exists, you cant delete it " + id);
                    throw new BadRequestException("Teacher with id %s does not exist, you cant delete it" + id);
                });

        teacherRepo.delete(teacher);
        log.info("Teacher with id {} has successfully delete from database"+id);

        return Response.builder()
                .httpStatus(HttpStatus.OK)
                .massage(String.format("Teacher with id %s has successfully delete from database"+id))
                .build();
    }

    @Transactional
    @Override
    public Response updateByTeacherId(Long id, TeacherRequestDto newTeacher) {
        TeacherResponseDto teacher = findByTeacherId(id);

        String currentFirstName = teacher.getFirstName();
        String newTeacherFirstName = newTeacher.getFirstName();

        if (!Objects.equals(currentFirstName,newTeacherFirstName)){
            teacher.setFirstName(newTeacherFirstName);
             log.info("Teacher name {} changed from {} to {} "+
                     id,currentFirstName,newTeacherFirstName);
        }
        String currentLastName = teacher.getLastName();
        String newTeacherLastName = newTeacher.getLastName();

        if (!Objects.equals(currentLastName,newTeacherLastName)){
            teacher.setLastName(newTeacherLastName);
            log.info("Teacher name {} changed from {} to {} "+
                    id,currentLastName,newTeacherLastName);
        }

        String currentEmail = teacher.getEmail();
        String newTeacherEmail = newTeacher.getEmail();
        if (!Objects.equals(currentEmail,newTeacherEmail)){
            teacher.setEmail(newTeacherEmail);
            log.info("Teacher name {} changed from {} to {} "+
                    id,currentEmail,newTeacherEmail);
        }
        return Response.builder()
                .httpStatus(HttpStatus.RESET_CONTENT)
                .massage(String.format("Teacher with id {} has successfully update from database"+id))
                .build();
    }

    @Override
    public List<TeacherResponseDto> getAllTeacher() {
        List<TeacherResponseDto> allTeacher = teacherRepo.findAll()
                .stream().map(teacher -> modelMapper.map(teacher, TeacherResponseDto.class))
                .toList();
        return allTeacher;
    }
}
