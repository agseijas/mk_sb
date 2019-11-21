package infra.entry;

import java.util.Optional;

import domain.shirts.ShirtFinder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindShirtCommand {

    private final ShirtFinder shirtFinder;

    public Optional<Shirt> find(Long id) {
        return shirtFinder.findBy(id)
                .map(this::adapt);
    }

    private Shirt adapt(domain.shirts.Shirt shirt) {
        return new Shirt(shirt.getId());
    }
}
