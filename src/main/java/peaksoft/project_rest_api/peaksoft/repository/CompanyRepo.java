package peaksoft.project_rest_api.peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.project_rest_api.peaksoft.entity.Company;


public interface CompanyRepo extends JpaRepository<Company, Long> {

    @Query("SELECT case when count (c) > 0 then true else false end " +
            "from Company c where c.companyName = :companyName")
    boolean existsByCompanyName(@Param("companyName") String companyName);
}
