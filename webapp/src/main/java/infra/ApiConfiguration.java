package infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import domain.sheets.SheetFinder;
import domain.shirts.ShirtFinder;
import domain.trousers.TrouserFinder;
import infra.api.ClosetApi;
import infra.entry.FindSheetCommand;
import infra.entry.FindShirtCommand;
import infra.entry.FindTrouserCommand;

@Configuration
//AN IDE SPRING'S INTEGRATION MIGHT COMPLAIN ABOUT NOT BEING ABLE TO FIND DOMAIN BEANS. I RECOMMEND
//NOT TO IMPORT THE DOMAIN CONFIGURATION, TO HAVE CLEARLY SEPARATED CONFIGURATION
public class ApiConfiguration {

    @Bean
    public FindShirtCommand findShirtCommand(ShirtFinder shirtFinder) {
        return new FindShirtCommand(shirtFinder);
    }


    @Bean
    public FindSheetCommand findSheetCommand(SheetFinder sheetFinder) {
        return new FindSheetCommand(sheetFinder);
    }

    @Bean
    public FindTrouserCommand findTrouserCommand(TrouserFinder trouserFinder) {
        return new FindTrouserCommand(trouserFinder);
    }

    @Bean
    public ClosetApi closetApi(FindShirtCommand findShirtCommand,
                               FindSheetCommand findSheetCommand,
                               FindTrouserCommand findTrouserCommand) {
        return new ClosetApi(findShirtCommand, findSheetCommand, findTrouserCommand);
    }

}
