package infra.api;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

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
        given(shirtCommand.find(1L)).willReturn(Optional.of(aShirt));

        Mono<Shirt> shirt = underTest.findShirt(1L);

        assertThat(shirt.block(), equalTo(aShirt));
    }

    @Test
    public void notFindsShirt() {
        given(shirtCommand.find(2L)).willReturn(empty());

        assertThrows(ShirtNotFound.class, () -> underTest.findShirt(2L));
    }

    @Test
    public void findsSheet() {
        given(sheetCommand.find(1L)).willReturn(Optional.of(aSheet));

        Mono<Sheet> sheet = underTest.findSheet(1L);

        assertThat(sheet.block(), equalTo(aSheet));
    }

    @Test
    public void notFindsSheet() {
        given(sheetCommand.find(2L)).willReturn(empty());

        assertThrows(SheetNotFound.class, () -> underTest.findSheet(2L));
    }

    @Test
    public void findsTrouser() {
        given(trouserCommand.find(1L)).willReturn(Optional.of(aTrouser));

        Mono<Trouser> trouser = underTest.findTrouser(1L);

        assertThat(trouser.block(), equalTo(aTrouser));
    }

    @Test
    public void notFindsTrouser() {
        given(trouserCommand.find(2L)).willReturn(empty());

        assertThrows(TrouserNotFound.class, () -> underTest.findTrouser(2L));
    }
}