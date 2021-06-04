package infra.api;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import infra.api.ClosetApi.SheetNotFound;
import infra.api.ClosetApi.ShirtNotFound;
import infra.api.ClosetApi.TrouserNotFound;
import infra.entry.FindSheetCommand;
import infra.entry.FindShirtCommand;
import infra.entry.FindTrouserCommand;
import infra.entry.Sheet;
import infra.entry.Shirt;
import infra.entry.Trouser;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class ClosetApiTest {

    @Mock
    private FindShirtCommand shirtCommand;
    @Mock
    private FindSheetCommand sheetCommand;
    @Mock
    private FindTrouserCommand trouserCommand;

    @InjectMocks
    private ClosetApi underTest;

    @Mock
    private Shirt aShirt;
    @Mock
    private Sheet aSheet;
    @Mock
    private Trouser aTrouser;


    @Test
    public void findsShirt() {
        when(shirtCommand.find(1L)).thenReturn(Optional.of(aShirt));

        Mono<Shirt> shirt = underTest.findShirt(1L);

        assertThat(shirt.block(), equalTo(aShirt));
    }

    @Test
    public void notFindsShirt() {
        when(shirtCommand.find(2L)).thenReturn(empty());

        assertThrows(ShirtNotFound.class, () -> underTest.findShirt(2L));
    }

    @Test
    public void findsSheet() {
        when(sheetCommand.find(1L)).thenReturn(Optional.of(aSheet));

        Mono<Sheet> sheet = underTest.findSheet(1L);

        assertThat(sheet.block(), equalTo(aSheet));
    }

    @Test
    public void notFindsSheet() {
        when(sheetCommand.find(2L)).thenReturn(empty());

        assertThrows(SheetNotFound.class, () -> underTest.findSheet(2L));
    }

    @Test
    public void findsTrouser() {
        when(trouserCommand.find(1L)).thenReturn(Optional.of(aTrouser));

        Mono<Trouser> trouser = underTest.findTrouser(1L);

        assertThat(trouser.block(), equalTo(aTrouser));
    }

    @Test
    public void notFindsTrouser() {
        when(trouserCommand.find(2L)).thenReturn(empty());

        assertThrows(TrouserNotFound.class, () -> underTest.findTrouser(2L));
    }
}