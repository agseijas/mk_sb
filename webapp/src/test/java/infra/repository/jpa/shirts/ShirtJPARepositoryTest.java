package infra.repository.jpa.shirts;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import domain.shirts.Shirt;

@ExtendWith(SpringExtension.class)
//SEE THAT THIS TEST OVERLAPS WITH THE INTEGRATION TEST NAMED SIMILARLY
//I RECOMMEND PERFORMING A SLICE TEST INSTEAD AND DELETING THIS.
public class ShirtJPARepositoryTest {

    @Mock
    private ShirtDAO dao;

    @InjectMocks
    private ShirtJPARepository underTest;

    @Test
    public void findsShirt() {
        Shirt expectedShirt = new Shirt(1L);
        when(dao.findById(1L)).thenReturn(Optional.of(new ShirtEntity(1L)));

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertEquals(Optional.of(expectedShirt), shirt);
    }

    @Test
    public void notFindsShirt() {
        when(dao.findById(1L)).thenReturn(empty());

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertThat(shirt, is(empty()));
    }
}