package peaksoft.project_rest_api.peaksoft.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CourseResponseDto {

   private Long id;

   private String courseName;

   private LocalDate finishedDate;
}
