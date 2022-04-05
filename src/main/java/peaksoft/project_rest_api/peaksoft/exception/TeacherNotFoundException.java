package peaksoft.project_rest_api.peaksoft.exception;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException() {
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
