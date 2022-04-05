package peaksoft.project_rest_api.peaksoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapper {

    @Bean
    public org.modelmapper.ModelMapper getModelMapper() {
        return new org.modelmapper.ModelMapper();
    }
}
