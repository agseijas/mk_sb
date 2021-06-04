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

import domain.shirts.ShirtFinder;

@ExtendWith(MockitoExtension.class)
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

        Optional<Shirt> shirt = underTest.find(1L);

        assertThat(shirt, is(empty()));
    }
}