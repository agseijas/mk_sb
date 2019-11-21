package domain.shirts;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShirtFinder {

    private final ShirtRepository repo;

    public Optional<Shirt> findBy(Long id) {
        return repo.findBy(id);
    }
}
