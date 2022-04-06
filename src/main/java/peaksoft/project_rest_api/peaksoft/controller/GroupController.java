package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.project_rest_api.peaksoft.dto.group.GroupRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.group.GroupResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.GroupService;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupResponseDto> getAllGroup() {
        return groupService.getAllGroup();
    }

    @PostMapping("/save")
    public Response saveGroup(@RequestBody GroupRequestDto group) {
        return groupService.saveGroup(group);
    }

    @GetMapping("/get/{groupId}")
    public GroupResponseDto findById(@PathVariable Long groupId) {
        return groupService.findByGroupId(groupId);
    }

    @DeleteMapping("/delete/{groupId}")
    public Response deleteById(@PathVariable("groupId") Long groupId) {
        return groupService.deleteByGroupId(groupId);
    }

    @PutMapping("/update/{groupId}")
    public Response updateById(@PathVariable Long groupId,
                               @RequestBody GroupRequestDto group) {
        return  groupService.updateByGroupId(groupId, group);
    }
}
