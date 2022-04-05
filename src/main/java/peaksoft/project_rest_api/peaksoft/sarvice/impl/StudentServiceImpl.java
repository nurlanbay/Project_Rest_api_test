package peaksoft.project_rest_api.peaksoft.sarvice.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest_tutorial.peaksoft.dto.student.StudentRequestDto;
import rest_tutorial.peaksoft.dto.student.StudentResponseDto;
import rest_tutorial.peaksoft.entity.Group;
import rest_tutorial.peaksoft.entity.Student;
import rest_tutorial.peaksoft.exception.BadRequestException;
import rest_tutorial.peaksoft.exception.NotFoundException;
import rest_tutorial.peaksoft.exception.StudentNotFoundException;
import rest_tutorial.peaksoft.exception.repository.GroupRepo;
import rest_tutorial.peaksoft.exception.repository.StudentRepo;
import rest_tutorial.peaksoft.response.Response;
import rest_tutorial.peaksoft.sarvice.StudentService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    // Dependency
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;
    private final GroupRepo groupRepo;

    @Override
    public Response saveStudent(StudentRequestDto newStudent) {
        String email = newStudent.getEmail();

        checkByEmail(email);
        Student student1 = modelMapper.map(newStudent, Student.class);
        Group group = groupRepo.findById(newStudent.getGroupId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("company with id = %d does not exists", newStudent.getGroupId())
                ));

        student1.setGroup(group);
        Student save = studentRepo.save(student1);


        log.info("student with email = {} has successfully saved to database", save.getEmail());
//     String massage = String.format("Student with email %s has successfully save to database");
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    public void checkByEmail(String email) {
        boolean exists = studentRepo.existsByEmail(email);

        if (exists) {
            log.info("Student with email {} has already exists" + email);
            throw new BadRequestException("Student with email %s has already exists" + email);
        }

    }

    @Override
    public StudentResponseDto findByStudentId(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> {
                    log.info("Student with id {} does not exists" + id);
                    throw new StudentNotFoundException(String.format(
                            "Student with id %s does not exists" + id));
                });
        log.info("found student with id" + id);
        return modelMapper.map(student,StudentResponseDto.class);
    }

    @Override
    public Response deleteByStudentId(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> {
                    log.info("Student with id {} does not exists, you cant delete it" + id);
                    throw new BadRequestException(
                            String.format("Student with id %s does not exists, you cant delete it" + id)
                    );
                });

        studentRepo.delete(student);

        log.info("Student with id {} has successfully delete from database" + id);

        return Response.builder()
                .httpStatus(HttpStatus.OK)
                .massage(String.format("Student with id = %s has successfully deleted", id))
                .build();
    }

    @Transactional
    @Override
    public Response updateByStudentId(Long id, StudentRequestDto newStudent) {
        StudentResponseDto student = findByStudentId(id);

        String currentName = student.getFirstName();
        String newStudentName = newStudent.getFirstName();

        if (!Objects.equals(currentName, newStudentName)) {
            student.setFirstName(newStudentName);
            log.info("Student with name = {} changed from {} to {}" +
                    id, currentName, newStudentName);
        }

        String currentLaseName = student.getLastName();
        String newStudentLastName = newStudent.getLastName();

        if (!Objects.equals(currentLaseName, newStudentLastName)) {
            student.setLastName(newStudentLastName);
            log.info("Student with name = {} changed from {} to {}" +
                    id, currentLaseName, newStudentLastName);
        }
        String currentEmail = student.getEmail();
        String newEmail = newStudent.getEmail();

        if (!Objects.equals(currentEmail, newEmail)) {
            student.setEmail(newEmail);
            log.info("Student with name = {} changed from {} to {}" +
                    id, currentEmail, newEmail);
        }
        String massage = String.format("Student with studentId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(HttpStatus.RESET_CONTENT)
                .massage(massage)
                .build();
    }

    @Override
    public List<StudentResponseDto> getAllStudent() {
        List<StudentResponseDto> allStudent = studentRepo.findAll()
                .stream().map(student -> modelMapper.map(student, StudentResponseDto.class)).toList();
        return allStudent;
    }
}
