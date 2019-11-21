package domain.sheets;

import java.util.Optional;

public interface SheetRepository {
    Optional<Sheet> findBy(Long id);
}
