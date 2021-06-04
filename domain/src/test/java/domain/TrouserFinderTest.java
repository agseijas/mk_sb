package domain;

import static java.util.Optional.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.trousers.Trouser;
import domain.trousers.TrouserFinder;
import domain.trousers.TrouserRepository;

@ExtendWith(MockitoExtension.class)
public class TrouserFinderTest {

    @Mock
    TrouserRepository repo;

    @InjectMocks
    public TrouserFinder underTest;

    @Test
    public void findTrouser() {
        Trouser expectedTrouser = new Trouser(1L);
        when(repo.findBy(1L)).thenReturn(of(expectedTrouser));

        Optional<Trouser> trouser = underTest.findBy(1L);

        assertEquals(Optional.of(expectedTrouser), trouser);
    }

    @Test
    public void doesntFind() {
        when(repo.findBy(1L)).thenReturn(empty());

        Optional<Trouser> trouser = underTest.findBy(1L);

        assertThat(trouser, is(empty()));
    }
}