package domain.sheets;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SheetFinder {

    private final SheetRepository repo;

    public Optional<Sheet> findBy(Long id) {
        return repo.findBy(id);
    }
}
