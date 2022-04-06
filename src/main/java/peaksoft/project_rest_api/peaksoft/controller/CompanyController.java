package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.project_rest_api.peaksoft.dto.company.CompanyRequestDto;
import peaksoft.project_rest_api.peaksoft.dto.company.CompanyResponseDto;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;
import peaksoft.project_rest_api.peaksoft.sarvice.CompanyService;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    // Dependency
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponseDto> getAllCompany()  {
        return companyService.getAllCompany();
    }

    @PostMapping("/save")
    public Response saveCompany(@RequestBody CompanyRequestDto company) {
        return companyService.saveCompany(company);
    }

    @GetMapping("/get/{companyId}")
    public CompanyResponseDto findById(@PathVariable("companyId")Long companyId) {
        return companyService.findByIdCompany(companyId);
    }

    @DeleteMapping("/delete/{companyId}")
    public Response deleteById(@PathVariable Long companyId) {
        return companyService.deleteByIdCompany(companyId);
    }

    @PutMapping("/update/{companyId}")
    public Response updateById(@PathVariable Long companyId,
                               @RequestBody CompanyRequestDto company) {
        return companyService.updateById(companyId,company);
    }
}

