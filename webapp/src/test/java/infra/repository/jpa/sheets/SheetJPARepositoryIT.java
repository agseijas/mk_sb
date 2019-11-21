package infra.repository.jpa.sheets;

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

import domain.sheets.Sheet;
import infra.RepositoryConfiguration.DataJPAConfiguration;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = DataJPAConfiguration.class)
public class SheetJPARepositoryIT {

    @Autowired
    private SheetJPARepository underTest;

    @Test
    @Sql(statements = "INSERT INTO SHEET (id) values (1);")
    public void findsSheet() {
        Sheet expectedShirt = new Sheet(1L);

        Optional<Sheet> sheet = underTest.findBy(1L);

        assertEquals(Optional.of(expectedShirt), sheet);
    }

    @Test
    public void notFindsSheet() {
        Optional<Sheet> sheet = underTest.findBy(1L);

        assertThat(sheet, is(empty()));
    }
}