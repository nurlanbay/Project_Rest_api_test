package peaksoft.project_rest_api.peaksoft.exception.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rest_tutorial.peaksoft.entity.Group;

public interface GroupRepo extends JpaRepository<Group,Long> {

    @Query("select case when count (g) > 0 then true else false end " +
            "from Group g where g.groupName = ?1")
    boolean existsByGroupName(String name);
}
