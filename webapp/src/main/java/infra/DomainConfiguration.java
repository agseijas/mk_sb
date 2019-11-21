package infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import domain.sheets.SheetFinder;
import domain.sheets.SheetRepository;
import domain.shirts.ShirtFinder;
import domain.shirts.ShirtRepository;
import domain.shoes.ShoeFinder;
import domain.shoes.ShoeRepository;
import domain.trousers.TrouserFinder;
import domain.trousers.TrouserRepository;

@Configuration
public class DomainConfiguration {

    @Bean
    public SheetFinder sheetFinder(SheetRepository repo) {
        return new SheetFinder(repo);
    }

    @Bean
    public ShirtFinder shirtFinder(ShirtRepository repo) {
        return new ShirtFinder(repo);
    }

    @Bean
    public TrouserFinder trouserFinder(TrouserRepository repo) {
        return new TrouserFinder(repo);
    }

    @Bean
    public ShoeFinder shoeFinder(ShoeRepository repo) {
        return new ShoeFinder(repo);
    }
}
