package infra.entry;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import domain.shirts.ShirtFinder;

@RunWith(MockitoJUnitRunner.class)
public class FindSheetCommandTest {

    @Mock
    ShirtFinder finder;

    @InjectMocks
    private FindShirtCommand underTest;

    private final domain.shirts.Shirt domainShirt = new domain.shirts.Shirt(1L);

    @Test
    public void findsShirt() {
        Shirt expectedShirt = new Shirt(1L);
        when(finder.findBy(1L)).thenReturn(Optional.of(domainShirt));

        Optional<Shirt> shirt = underTest.find(1L);

        assertEquals(Optional.of(expectedShirt), shirt);
    }

    @Test
    public void notFindsShirt() {
        when(finder.findBy(1L)).thenReturn(empty());

        Optional shirt = underTest.find(1L);

        assertThat(shirt, is(empty()));
    }
}