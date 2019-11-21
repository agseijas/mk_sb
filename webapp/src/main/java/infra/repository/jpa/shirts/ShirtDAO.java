package infra.repository.jpa.shirts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShirtDAO extends JpaRepository<ShirtEntity, Long> {
}
