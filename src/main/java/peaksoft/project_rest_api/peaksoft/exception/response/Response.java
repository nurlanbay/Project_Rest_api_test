package peaksoft.project_rest_api.peaksoft.exception.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private HttpStatus httpStatus;
    private String massage;
}
