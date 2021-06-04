package domain;

import static java.util.Optional.*;
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

import domain.shirts.Shirt;
import domain.shirts.ShirtFinder;
import domain.shirts.ShirtRepository;

@ExtendWith(MockitoExtension.class)
public class ShirtFinderTest {

    @Mock
    ShirtRepository repo;

    @InjectMocks
    public ShirtFinder underTest;

    @Test
    public void findShirt() {
        Shirt expectedShirt = new Shirt(1L);
        given(repo.findBy(1L)).willReturn(of(expectedShirt));

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertEquals(Optional.of(expectedShirt), shirt);
    }

    @Test
    public void doesntFind() {
        given(repo.findBy(1L)).willReturn(empty());

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertThat(shirt, is(empty()));
    }
}