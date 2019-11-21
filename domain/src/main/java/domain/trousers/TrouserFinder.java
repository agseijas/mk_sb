package domain.trousers;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrouserFinder {

    private final TrouserRepository repo;

    public Optional<Trouser> findBy(Long id) {
        return repo.findBy(id);
    }
}
