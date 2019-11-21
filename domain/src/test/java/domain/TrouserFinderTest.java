package domain;

import static java.util.Optional.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import domain.trousers.Trouser;
import domain.trousers.TrouserFinder;
import domain.trousers.TrouserRepository;

@RunWith(MockitoJUnitRunner.class)
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