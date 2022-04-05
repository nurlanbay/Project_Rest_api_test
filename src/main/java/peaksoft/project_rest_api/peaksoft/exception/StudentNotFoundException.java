package peaksoft.project_rest_api.peaksoft.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
