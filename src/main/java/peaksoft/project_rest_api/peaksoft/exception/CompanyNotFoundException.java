package peaksoft.project_rest_api.peaksoft.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
