package infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import domain.sheets.SheetRepository;
import domain.shirts.ShirtRepository;
import domain.shoes.ShoeRepository;
import domain.trousers.TrouserRepository;
import infra.repository.jpa.sheets.SheetDAO;
import infra.repository.jpa.sheets.SheetEntity;
import infra.repository.jpa.sheets.SheetJPARepository;
import infra.repository.jpa.shirts.ShirtDAO;
import infra.repository.jpa.shirts.ShirtEntity;
import infra.repository.jpa.shirts.ShirtJPARepository;
import infra.repository.jpa.shoe.ShoeDAO;
import infra.repository.jpa.shoe.ShoeEntity;
import infra.repository.jpa.shoe.ShoeJPARepository;
import infra.repository.rest.trousers.TrouserRestRepository;

@Configuration
//REPOSITORIES CONFIGURATION ARE SPLIT SO THAT SLICES CAN BE CONFIGURED SEPARATELY WITH @ContextConfiguration IN TESTS
// YOU CAN SPLIT THESE EVEN FURTHER (SEPARATING SHIRT AND SHEET)
public class RepositoryConfiguration {

    @Configuration
    @EnableJpaRepositories(basePackageClasses = {ShirtJPARepository.class, SheetJPARepository.class, ShoeJPARepository.class })
    @EntityScan(basePackageClasses = { SheetEntity.class, ShirtEntity.class, ShoeEntity.class} )
    public static class DataJPAConfiguration {
        @Bean
        ShirtRepository shirtRepository(ShirtDAO dao){
            return new ShirtJPARepository(dao);
        }

        @Bean
        SheetRepository sheetRepository(SheetDAO dao){
            return new SheetJPARepository(dao);
        }

        @Bean
        ShoeRepository shoeRepository(ShoeDAO dao){
            return new ShoeJPARepository(dao);
        }
    }

    @Configuration
    public static class RestConfiguration {

        @Bean
        RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder();
        }

        @Bean
        RestTemplate trousersRestTemplate(RestTemplateBuilder builder,
                                          @Value("${trousers.uri:/trousers-repo}") String uri){
            return builder.rootUri(uri).build();
        }

        @Bean
        TrouserRepository trouserRestRepository(RestTemplate trousersRestTemplate) {
            return new TrouserRestRepository(trousersRestTemplate);
        }
    }
}
