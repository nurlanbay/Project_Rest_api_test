package peaksoft.project_rest_api.peaksoft.dto.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDto {
    private String courseName;
    private int durationMonth;
    private Long companyId;

}
