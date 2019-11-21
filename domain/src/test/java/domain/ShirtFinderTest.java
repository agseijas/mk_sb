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

import domain.shirts.Shirt;
import domain.shirts.ShirtFinder;
import domain.shirts.ShirtRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShirtFinderTest {

    @Mock
    ShirtRepository repo;

    @InjectMocks
    public ShirtFinder underTest;

    @Test
    public void findShirt() {
        Shirt expectedShirt = new Shirt(1L);
        when(repo.findBy(1L)).thenReturn(of(expectedShirt));

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertEquals(Optional.of(expectedShirt), shirt);
    }

    @Test
    public void doesntFind() {
        when(repo.findBy(1L)).thenReturn(empty());

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertThat(shirt, is(empty()));
    }
}