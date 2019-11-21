package infra.entry;

import java.util.Optional;

import domain.sheets.SheetFinder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindSheetCommand {

    private final SheetFinder useCase;

    public Optional<Sheet> find(Long id) {
        return useCase.findBy(id)
                .map(this::adapt);
    }

    private Sheet adapt(domain.sheets.Sheet sheet) {
        return new Sheet(sheet.getId());
    }
}
