package infra.entry;

import java.util.Optional;

import domain.trousers.TrouserFinder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindTrouserCommand {

    private final TrouserFinder useCase;

    public Optional<Trouser> find(Long id) {
        return useCase.findBy(id)
                .map(this::adapt);
    }

    private Trouser adapt(domain.trousers.Trouser trouser) {
        return new Trouser(trouser.getId());
    }
}
