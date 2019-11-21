package domain.trousers;

import java.util.Optional;

public interface TrouserRepository {
    Optional<Trouser> findBy(Long id);
}
