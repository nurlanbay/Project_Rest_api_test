package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_tutorial.peaksoft.dto.group.GroupRequestDto;
import rest_tutorial.peaksoft.dto.group.GroupResponseDto;
import rest_tutorial.peaksoft.exception.BadRequestException;
import rest_tutorial.peaksoft.exception.CompanyNotFoundException;
import rest_tutorial.peaksoft.response.Response;
import rest_tutorial.peaksoft.sarvice.GroupService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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

    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(CompanyNotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .massage(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .massage(badRequestException.getMessage())
                .build();
    }
}
