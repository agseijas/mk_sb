package domain;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import domain.sheets.Sheet;
import domain.sheets.SheetFinder;
import domain.sheets.SheetRepository;

@RunWith(MockitoJUnitRunner.class)
public class SheetFinderTest {

    @Mock
    SheetRepository repo;

    @InjectMocks
    SheetFinder underTest;

    private static final long anySheetId = 1L;

    @Test
    public void findSheet() {
        Sheet expectedSheet = new Sheet(anySheetId);
        when(repo.findBy(anySheetId)).thenReturn(Optional.of(expectedSheet));

        Optional<Sheet> sheet = underTest.findBy(anySheetId);

        assertEquals(Optional.of(expectedSheet), sheet);
    }

    @Test
    public void doesntFind() {
        when(repo.findBy(anySheetId)).thenReturn(empty());

        Optional<Sheet> sheet = underTest.findBy(anySheetId);

        assertThat(sheet, is(empty()));
    }
}