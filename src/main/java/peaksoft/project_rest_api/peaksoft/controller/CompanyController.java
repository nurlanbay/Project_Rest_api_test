package peaksoft.project_rest_api.peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_tutorial.peaksoft.dto.company.CompanyRequestDto;
import rest_tutorial.peaksoft.dto.company.CompanyResponseDto;
import rest_tutorial.peaksoft.exception.BadRequestException;
import rest_tutorial.peaksoft.exception.CompanyNotFoundException;
import rest_tutorial.peaksoft.response.Response;
import rest_tutorial.peaksoft.sarvice.CompanyService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


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

