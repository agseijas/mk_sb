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

import domain.shoes.Shoe;
import domain.shoes.ShoeFinder;
import domain.shoes.ShoeRepository;

@ExtendWith(MockitoExtension.class)
public class ShoeFinderTest {

    @Mock
    ShoeRepository repo;

    @InjectMocks
    public ShoeFinder underTest;

    @Test
    public void findShoe() {
        Shoe expectedShoe = new Shoe(1L);
        when(repo.findBy(1L)).thenReturn(of(expectedShoe));

        Optional<Shoe> shoe = underTest.findBy(1L);

        assertEquals(Optional.of(expectedShoe), shoe);
    }

    @Test
    public void doesntFind() {
        when(repo.findBy(1L)).thenReturn(empty());

        Optional<Shoe> shoe = underTest.findBy(1L);

        assertThat(shoe, is(empty()));
    }
}