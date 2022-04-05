package peaksoft.project_rest_api.peaksoft.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class Response {
    private HttpStatus httpStatus;
    private String massage;
}
