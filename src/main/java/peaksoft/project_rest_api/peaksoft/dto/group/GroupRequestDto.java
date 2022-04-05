package peaksoft.project_rest_api.peaksoft.dto.group;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
public class GroupRequestDto {

    String groupName;

    int dateOfStart;

    int dateOfFinish;

    Long courseId;

}
