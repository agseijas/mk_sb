package domain.shoes;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoeFinder {
    private final ShoeRepository repo;

    public Optional<Shoe> findBy(Long id) {
        return repo.findBy(id);
    }
}
