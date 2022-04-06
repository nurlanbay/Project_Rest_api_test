package peaksoft.project_rest_api.peaksoft.sarvice;


import peaksoft.project_rest_api.peaksoft.dto.group.GroupRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.group.GroupResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;

import java.util.List;

public interface GroupService {

    Response saveGroup(GroupRequestDto group);

    GroupResponseDto findByGroupId(Long id);

    Response deleteByGroupId(Long id);

    Response updateByGroupId(Long id, GroupRequestDto group);

    List<GroupResponseDto> getAllGroup();
}
