package peaksoft.project_rest_api.peaksoft.dto.teacher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class TeacherRequestDto {

    String firstName;

    String lastName;

    String email;

    Long courseId;
}
