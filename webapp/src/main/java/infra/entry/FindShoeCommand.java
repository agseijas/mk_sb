package infra.entry;

import java.util.Optional;

import domain.shoes.ShoeFinder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindShoeCommand {

    private final ShoeFinder useCase;

    public Optional<Shoe> find(Long id) {
        return useCase.findBy(id)
                .map(this::adapt);
    }

    private Shoe adapt(domain.shoes.Shoe shoe) {
        return new Shoe(shoe.getId());
    }
}
