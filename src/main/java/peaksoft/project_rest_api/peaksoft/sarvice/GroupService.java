package peaksoft.project_rest_api.peaksoft.sarvice;

import rest_tutorial.peaksoft.dto.group.GroupRequestDto;
import rest_tutorial.peaksoft.dto.group.GroupResponseDto;
import rest_tutorial.peaksoft.response.Response;

import java.util.List;

public interface GroupService {

    Response saveGroup(GroupRequestDto group);

    GroupResponseDto findByGroupId(Long id);

    Response deleteByGroupId(Long id);

    Response updateByGroupId(Long id, GroupRequestDto group);

    List<GroupResponseDto> getAllGroup();
}
