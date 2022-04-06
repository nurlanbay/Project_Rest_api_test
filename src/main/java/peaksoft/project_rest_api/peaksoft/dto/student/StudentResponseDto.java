package peaksoft.project_rest_api.peaksoft.dto.student;

import lombok.Getter;
import lombok.Setter;
import peaksoft.project_rest_api.peaksoft.entity.StudyFormat;


@Getter @Setter
public class StudentResponseDto {

   private Long id;

   private String firstName;

   private String lastName;

   private String email;

   private StudyFormat studyFormat;
}
