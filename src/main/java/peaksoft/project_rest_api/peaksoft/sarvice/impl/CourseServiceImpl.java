package peaksoft.project_rest_api.peaksoft.sarvice.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.project_rest_api.peaksoft.dto.course.CourseRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.course.CourseResponseDto;
import peaksoft.project_rest_api.peaksoft.entity.Company;
import peaksoft.project_rest_api.peaksoft.entity.Course;
import peaksoft.project_rest_api.peaksoft.exception.BadRequestException;
import peaksoft.project_rest_api.peaksoft.exception.CompanyNotFoundException;
import peaksoft.project_rest_api.peaksoft.exception.NotFoundException;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.repository.CompanyRepo;
import peaksoft.project_rest_api.peaksoft.repository.CourseRepo;
import peaksoft.project_rest_api.peaksoft.sarvice.CourseService;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final ModelMapper modelMapper;
    private final CompanyRepo companyRepo;

    @Override
    @Transactional
    public Response saveCourse(CourseRequestDto newCourse) {
        String courseName = newCourse.getCourseName();

        checkCourseName(courseName);

        Course course = modelMapper.map(newCourse, Course.class);

        course.setFinishedDate(LocalDate.now().plusMonths(newCourse.getDurationMonth()));

        Company company = companyRepo.findById(newCourse.getCompanyId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("company with id = %d does not exists", newCourse.getCompanyId())
                ));

        course.setCompany(company);

        courseRepo.save(course);
        log.info("Course with name {} has successfully save to database" + course.getCourseName());

        return Response.builder()
                .httpStatus(CREATED)
                .massage(String.format("Course with name %s has successfully save to database", course.getCourseName()))
                .build();
    }

    public void checkCourseName(String name) {
        boolean exists = courseRepo.existsByCourseName(name);
        if (exists) {
            log.info("Course with name = {} already exists" + name);
            throw new BadRequestException("Course with name =" + name + "already exists");
        }
    }

    @Override
    public CourseResponseDto findByCourseId(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("student with id = {} does not exists", id);
                    throw new CompanyNotFoundException(
                            String.format("student with id = %s does not exists", id)
                    );
                });

        log.info("founded student with id = {}", id);

        return modelMapper.map(course,CourseResponseDto.class);
    }

    @Override
    public Response deleteByIdCourse(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> {
                    throw new BadRequestException(
                            String.format("course with id = %s does not exists, you can't delete it", id)
                    );
                });

        courseRepo.delete(course);
        log.info("Course with id = %s successfully delete in database" + id);

        return Response
                .builder()
                .httpStatus(OK)
                .massage(String.format("Course with id = %s has successfully deleted", id))
                .build();
    }

    @Transactional
    @Override
    public Response updateById(Long id, CourseRequestDto newCourse) {
        CourseResponseDto course1 = findByCourseId(id);

        String newCourseName = newCourse.getCourseName();
        String courseName1 = course1.getCourseName();

        if (!Objects.equals(newCourseName, courseName1)) {
            course1.setCourseName(newCourseName);
            log.info("Course with id {} = changed from currentName {} to {} ",
                    id, courseName1, newCourseName);
        }

        String format = String.format("Course with id = %d duration month changed successfully update", id);

        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .massage(format)
                .build();
    }

    @Override
    public List<CourseResponseDto> getAllCourse() {
        return courseRepo.findAll()
                .stream().map(course -> modelMapper.map(course, CourseResponseDto.class)).toList();
    }
}
