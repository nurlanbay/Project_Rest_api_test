package peaksoft.project_rest_api.peaksoft.dto.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDto  {

    @JsonProperty("companyId")
    private Long id;

    private String companyName;

    private String locatedCountry;
}
