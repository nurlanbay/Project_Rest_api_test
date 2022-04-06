package peaksoft.project_rest_api.peaksoft.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.project_rest_api.peaksoft.exception.BadRequestException;
import peaksoft.project_rest_api.peaksoft.exception.CompanyNotFoundException;
import peaksoft.project_rest_api.peaksoft.exception.response.Response;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
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
