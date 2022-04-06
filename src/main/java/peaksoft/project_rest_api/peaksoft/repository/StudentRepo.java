package peaksoft.project_rest_api.peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.project_rest_api.peaksoft.entity.Student;

public interface StudentRepo extends JpaRepository<Student,Long> {
    @Query("select case when count (s) > 0 then true else false end " +
            "from Student s where s.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
