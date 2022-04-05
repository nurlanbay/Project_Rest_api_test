package peaksoft.project_rest_api.peaksoft.sarvice;
import rest_tutorial.peaksoft.dto.company.CompanyRequestDto;
import rest_tutorial.peaksoft.dto.company.CompanyResponseDto;
import rest_tutorial.peaksoft.response.Response;

import java.util.List;



public interface CompanyService {

    Response saveCompany(CompanyRequestDto company);

    CompanyResponseDto findByIdCompany(Long id);

    Response deleteByIdCompany(Long id);

    Response updateById(Long companyId, CompanyRequestDto newCompany);

    List<CompanyResponseDto> getAllCompany();
}
