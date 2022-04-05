package peaksoft.project_rest_api.peaksoft.dto.group;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class GroupResponseDto {

   private Long id;

   private String groupName;

   private LocalDate dateOfStart;

   private LocalDate dateOfFinish;
}
