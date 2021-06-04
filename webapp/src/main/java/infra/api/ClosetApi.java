package infra.api;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import infra.entry.FindSheetCommand;
import infra.entry.FindShirtCommand;
import infra.entry.FindShoeCommand;
import infra.entry.FindTrouserCommand;
import infra.entry.Sheet;
import infra.entry.Shirt;
import infra.entry.Shoe;
import infra.entry.Trouser;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

//You might use the functional endpoints alternative instead.
@RequiredArgsConstructor
@RestController
public class ClosetApi {

    private final FindShirtCommand findShirtCommand;
    private final FindSheetCommand findSheetCommand;
    private final FindTrouserCommand findTrouserCommand;
    private final FindShoeCommand findShoeCommand;

    @GetMapping(value = "/shirt/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Shirt> findShirt(@PathVariable Long id) {
        return findShirtCommand.find(id)
                .map(Mono::just)
                .orElseThrow(ShirtNotFound::new);
    }

    @GetMapping(value = "/sheet/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Sheet> findSheet(@PathVariable Long id) {
        return findSheetCommand.find(id)
                .map(Mono::justOrEmpty)
                .orElseThrow(SheetNotFound::new);
    }

    @GetMapping(value = "/trouser/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Trouser> findTrouser(@PathVariable Long id) {
        return findTrouserCommand.find(id)
                .map(Mono::justOrEmpty)
                .orElseThrow(TrouserNotFound::new);
    }

    @GetMapping(value = "/shoe/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Shoe> findShoe(@PathVariable Long id) {
        return findShoeCommand.find(id)
                .map(Mono::justOrEmpty)
                .orElseThrow(ShoeNotFound::new);
    }

    @ResponseStatus(code = NOT_FOUND, reason = "shirt not found")
    static class ShirtNotFound extends RuntimeException {}

    @ResponseStatus(code = NOT_FOUND, reason = "sheet not found")
    static class SheetNotFound extends RuntimeException {}

    @ResponseStatus(code = NOT_FOUND, reason = "trouser not found")
    static class TrouserNotFound extends RuntimeException {}

    @ResponseStatus(code = NOT_FOUND, reason = "shoe not found")
    static class ShoeNotFound extends RuntimeException {}
}
