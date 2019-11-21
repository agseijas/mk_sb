package infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//THIS LOOKS LIKE IT SHOULD BE THE @SpringBootConfiguration BUT BEWARE,
// SINCE ALL Spring Boot TEST ANNOTATION WILL LOAD IT (HENCE LOADING ALL CONFIGURATIONS)
@Configuration
@Import( {DomainConfiguration.class, ApiConfiguration.class, RepositoryConfiguration.class })
public class ClosetConfiguration {

}
