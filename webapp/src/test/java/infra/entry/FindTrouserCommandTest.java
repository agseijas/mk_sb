package infra.entry;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.trousers.TrouserFinder;

@ExtendWith(MockitoExtension.class)
public class FindTrouserCommandTest {

    @Mock
    TrouserFinder finder;

    @InjectMocks
    private FindTrouserCommand underTest;

    private final domain.trousers.Trouser domainTrouser = new domain.trousers.Trouser(1L);

    @Test
    public void findsTrouser() {
        Trouser expectedTrouser = new Trouser(1L);
        when(finder.findBy(1L)).thenReturn(Optional.of(domainTrouser));

        Optional<Trouser> trouser = underTest.find(1L);

        assertEquals(Optional.of(expectedTrouser), trouser);
    }

    @Test
    public void notFindsTrouser() {
        when(finder.findBy(1L)).thenReturn(empty());

        Optional trouser = underTest.find(1L);

        assertThat(trouser, is(empty()));
    }
}