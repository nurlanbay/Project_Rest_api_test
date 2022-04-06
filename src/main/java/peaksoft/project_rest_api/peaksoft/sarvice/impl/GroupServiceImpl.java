package peaksoft.project_rest_api.peaksoft.sarvice.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.project_rest_api.peaksoft.dto.group.GroupRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.group.GroupResponseDto;
import peaksoft.project_rest_api.peaksoft.entity.Course;
import peaksoft.project_rest_api.peaksoft.entity.Group;
import peaksoft.project_rest_api.peaksoft.exception.BadRequestException;
import peaksoft.project_rest_api.peaksoft.exception.GroupNotFoundException;
import peaksoft.project_rest_api.peaksoft.exception.NotFoundException;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.repository.CourseRepo;
import peaksoft.project_rest_api.peaksoft.repository.GroupRepo;
import peaksoft.project_rest_api.peaksoft.sarvice.GroupService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    // Dependency
    private final GroupRepo groupRepo;
    private final ModelMapper modelMapper;
    private final CourseRepo courseRepo;


    @Override
    public Response saveGroup(GroupRequestDto group) {
        String groupName = group.getGroupName();

        checkGroupName(groupName);
        Group group1 = modelMapper.map(group, Group.class);

        group1.setDateOfStart(LocalDate.now().plusMonths(group.getDateOfStart()));

        Course course = courseRepo.findById(group.getCourseId())
                .orElseThrow(() -> new NotFoundException(
                        String.format("company with id = %d does not exists", group.getCourseId())
                ));

        group1.addCourse(course);
         groupRepo.save(group1);

        log.info("Group with name = {} has successfully save to database" + group.getGroupName());

        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
//                .massage(String.format("Group with name %s has successfully save to database"))
                .build();
    }

    public void checkGroupName(String name) {
        boolean exists = groupRepo.existsByGroupName(name);
        if (exists) {
            log.info("Group with name = {} already exists" + name);
            throw new BadRequestException("group with naem " + name + "already exists");
        }
    }

    @Override
    public GroupResponseDto findByGroupId(Long id) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> {
                    log.info("Group with id = {} does not exists" + id);
                    throw new GroupNotFoundException(
                            String.format("Group with id %s does not exists" + id));
                });
        log.info("Found Group with id {} "+id);

           return modelMapper.map(group,GroupResponseDto.class);
    }

    @Override
    public Response deleteByGroupId(Long id) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> {
                    log.info("Group with id = {} does not exists, you cant delete it", id);
                    throw new BadRequestException(
                            String.format("Group wit  h id %s does not exists, you cant delete it" + id));
                });

        groupRepo.delete(group);

        log.info("Group with id {} has successfully deleted from database"+id);


        return Response.builder()
                .httpStatus(HttpStatus.OK)
                .massage(String.format("Group with id %s does not exists, you cant delete it"+id))
                .build();
    }

    @Transactional
    @Override
    public Response updateByGroupId(Long id, GroupRequestDto newGroup) {
        GroupResponseDto group = findByGroupId(id);

        String currentName = group.getGroupName();
        String newGroupName = newGroup.getGroupName();

        if (!Objects.equals(currentName,newGroupName)){
          group.setGroupName(newGroupName);
          log.info("Group with name = {} changed from {} to {}"+
                  id,currentName,newGroupName);
        }
        LocalDate currenStart = group.getDateOfStart();
        int newStart = newGroup.getDateOfStart();

        if (!Objects.equals(currenStart,newStart)){
            group.setDateOfStart(LocalDate.ofEpochDay(newStart));
            log.info("Group with name = {} changed from {} to {}"+
                    id,currenStart,newStart);
        }
        LocalDate currentFinish = group.getDateOfFinish();
        int newFinish = newGroup.getDateOfFinish();

        if (!Objects.equals(currentFinish,newFinish)){
            group.setDateOfFinish(LocalDate.ofEpochDay(newFinish));
            log.info("Group with name = {} changed from {} to {}"+
                    id,currentFinish,newFinish);
        }
      String massage = String.format("Group with id = %s has successfully update",id);

        return Response.builder()
                .httpStatus(HttpStatus.RESET_CONTENT)
                .massage(massage)
                .build();
    }

    @Override
    public List<GroupResponseDto> getAllGroup() {
        List<GroupResponseDto> allGroups = groupRepo.findAll()
                .stream().map(group -> modelMapper.map(group, GroupResponseDto.class))
                .toList();
        return allGroups;
    }
}
