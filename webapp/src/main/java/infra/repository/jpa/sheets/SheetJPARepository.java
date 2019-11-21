package infra.repository.jpa.sheets;

import java.util.Optional;

import domain.sheets.Sheet;
import domain.sheets.SheetRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SheetJPARepository implements SheetRepository {

    private final SheetDAO dao;

    @Override
    public Optional<Sheet> findBy(Long id) {
        return dao.findById(id).map(this::adapt);
    }

    private Sheet adapt(SheetEntity sheetEntity) {
        return new Sheet(sheetEntity.getId());
    }

}
