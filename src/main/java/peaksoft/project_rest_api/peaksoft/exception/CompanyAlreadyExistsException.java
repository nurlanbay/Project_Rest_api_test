package peaksoft.project_rest_api.peaksoft.exception;

public class CompanyAlreadyExistsException extends RuntimeException {
    public CompanyAlreadyExistsException() {
    }

    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
