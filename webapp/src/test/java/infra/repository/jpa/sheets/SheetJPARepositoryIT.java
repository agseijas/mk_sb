package infra.repository.jpa.sheets;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import domain.sheets.Sheet;
import infra.RepositoryConfiguration.DataJPAConfiguration;

@ExtendWith(SpringExtension.class)
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