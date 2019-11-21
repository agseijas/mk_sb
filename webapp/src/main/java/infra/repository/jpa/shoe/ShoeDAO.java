package infra.repository.jpa.shoe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeDAO extends JpaRepository<ShoeEntity, Long> {
}
