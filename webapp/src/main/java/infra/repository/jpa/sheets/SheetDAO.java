package infra.repository.jpa.sheets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetDAO extends JpaRepository<SheetEntity, Long> {
}
