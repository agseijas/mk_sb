package infra.repository.jpa.shirts;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import domain.shirts.Shirt;
import infra.RepositoryConfiguration.DataJPAConfiguration;

@RunWith(SpringRunner.class)
//SEE THAT THIS TEST OVERLAPS WITH THE UNIT TEST NAMED SIMILARLY
//I RECOMMEND TO KEEP THIS TEST AND REMOVE THE UNIT TEST
@DataJpaTest
@ContextConfiguration(classes = DataJPAConfiguration.class)
public class ShirtJPARepositoryIT {

    @Autowired
    private ShirtJPARepository underTest;

    @Test
    @Sql(statements = "INSERT INTO SHIRT (id) values (1);")
    public void findsShirt() {
        Shirt expectedShirt = new Shirt(1L);

        Optional<Shirt> shirt = underTest.findBy(1L);

        assertEquals(Optional.of(expectedShirt), shirt);
    }

    @Test
    public void notFindsShirt() {
        Optional<Shirt> shirt = underTest.findBy(1L);

        assertThat(shirt, is(empty()));
    }

}