package peaksoft.project_rest_api.peaksoft.sarvice.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.project_rest_api.peaksoft.dto.company.CompanyRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.company.CompanyResponseDto;
import peaksoft.project_rest_api.peaksoft.entity.Company;
import peaksoft.project_rest_api.peaksoft.exception.BadRequestException;
import peaksoft.project_rest_api.peaksoft.exception.CompanyNotFoundException;
import peaksoft.project_rest_api.peaksoft.repository.CompanyRepo;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.CompanyService;
import java.util.List;
import java.util.Objects;
import static org.springframework.http.HttpStatus.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    // Dependency
    private final CompanyRepo companyRepo;
    private final ModelMapper modelMapper;

    @Override
    public Response saveCompany(CompanyRequestDto company) {
        String companyName = company.getCompanyName();

        checkName(companyName);

        Company aCompany = modelMapper.map(company, Company.class);
        Company saved = companyRepo.save(aCompany);

        log.info("Company with name {} has successfully save to database" + saved.getCompanyName());
        return Response.builder()
                .httpStatus(CREATED)
                .massage(String.format("Company with name {} has successfully save to database" + saved.getCompanyName()))
                .build();
    }

    public void checkName(String companyName){
        boolean exists = companyRepo.existsByCompanyName(companyName);
        if (exists){
         log.info("Company with name = {} already exists",companyName);
         throw new BadRequestException("" +
                 "Company with name = "+companyName+"already exists");
        }
   }

    @Override
    public CompanyResponseDto findByIdCompany(Long id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("student with id = {} does not exists", id);
                    throw new CompanyNotFoundException(
                            String.format("student with id = %s does not exists", id)
                    );
                });

        log.info("founded student with id = {}", id);

        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    public Response deleteByIdCompany(Long id) {
        Company company = companyRepo.findById(id).
                orElseThrow(() -> {
                    throw new CompanyNotFoundException();
                });

        companyRepo.delete(company);
    return Response.builder()
                .httpStatus(OK)
                .massage(String.format("Course with id = %s has successfully deleted", id))
                .build();
    }

    @Transactional
    @Override
    public Response updateById(Long companyId, CompanyRequestDto newCompany) {
        CompanyResponseDto company = findByIdCompany(companyId);

        String currentCompanyName = company.getCompanyName();
        String newCompanyName = newCompany.getCompanyName();

        if (!Objects.equals(currentCompanyName, newCompanyName)) {
            company.setCompanyName(newCompanyName);
            log.info("Company with id {} changed full name from {} to {}"
                    + companyId, currentCompanyName, newCompany);
        }

        String CurrenCountryName = company.getLocatedCountry();
        String newCompanyLocatedCountry = newCompany.getLocatedCountry();

        if (!Objects.equals(currentCompanyName, newCompanyLocatedCountry)) {
            company.setLocatedCountry(newCompanyLocatedCountry);
            log.info("Company with id {} changed full name from {} to {}" +
                    companyId, CurrenCountryName, newCompanyLocatedCountry);
        }
        String message = String.format("Student with studentId = %s has successfully updated", companyId);
        return Response
                .builder()
                .httpStatus(RESET_CONTENT)
                .massage(message)
                .build();
    }

    @Override
    public List<CompanyResponseDto> getAllCompany() {
        List<CompanyResponseDto> allCompany = companyRepo.findAll()
                .stream()
                .map(company -> modelMapper.map(company, CompanyResponseDto.class))
                .toList();
        allCompany.forEach(System.out::println);
        log.info("founded{} students", allCompany.size());
        return allCompany;
    }
}
