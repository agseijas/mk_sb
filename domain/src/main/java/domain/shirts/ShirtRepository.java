package domain.shirts;

import java.util.Optional;

public interface ShirtRepository {
    Optional<Shirt> findBy(Long id);
}
