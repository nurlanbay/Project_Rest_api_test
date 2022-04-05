package peaksoft.project_rest_api.peaksoft.init;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rest_tutorial.peaksoft.entity.*;
import rest_tutorial.peaksoft.sarvice.CompanyService;

@Component
@Transactional
public class DbInit {


    // Dependency
    private final CompanyService companyDao;

     // Injection
    public DbInit(CompanyService companyDao) {
        this.companyDao = companyDao;
    }

//    @PostConstruct
    public void init() {

    }
}
