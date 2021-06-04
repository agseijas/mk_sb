package domain;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.sheets.Sheet;
import domain.sheets.SheetFinder;
import domain.sheets.SheetRepository;

@ExtendWith(MockitoExtension.class)
public class SheetFinderTest {

    @Mock
    SheetRepository repo;

    @InjectMocks
    SheetFinder underTest;

    private static final long anySheetId = 1L;

    @Test
    public void findSheet() {
        Sheet expectedSheet = new Sheet(anySheetId);
        given(repo.findBy(anySheetId)).willReturn(Optional.of(expectedSheet));

        Optional<Sheet> sheet = underTest.findBy(anySheetId);

        assertEquals(Optional.of(expectedSheet), sheet);
    }

    @Test
    public void doesntFind() {
        given(repo.findBy(anySheetId)).willReturn(empty());

        Optional<Sheet> sheet = underTest.findBy(anySheetId);

        assertThat(sheet, is(empty()));
    }
}