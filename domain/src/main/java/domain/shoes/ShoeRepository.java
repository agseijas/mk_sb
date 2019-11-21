package domain.shoes;

import java.util.Optional;

public interface ShoeRepository {
    Optional<Shoe> findBy(Long id);
}
