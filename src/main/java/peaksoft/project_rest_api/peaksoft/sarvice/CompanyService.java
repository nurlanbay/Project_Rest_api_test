package peaksoft.project_rest_api.peaksoft.sarvice;


import peaksoft.project_rest_api.peaksoft.dto.company.CompanyRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.company.CompanyResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;

import java.util.List;

public interface CompanyService {

    Response saveCompany(CompanyRequestDto company);

    CompanyResponseDto findByIdCompany(Long id);

    Response deleteByIdCompany(Long id);

    Response updateById(Long companyId, CompanyRequestDto newCompany);

    List<CompanyResponseDto> getAllCompany();
}
