package infra.repository.jpa.shirts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShirtDAO extends JpaRepository<ShirtEntity, Long> {

    @Override
    Optional<ShirtEntity> findById(Long id);
}
