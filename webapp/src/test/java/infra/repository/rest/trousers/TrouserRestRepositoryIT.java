package infra.repository.rest.trousers;

import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import domain.trousers.Trouser;
import infra.RepositoryConfiguration.RestConfiguration;

@RunWith(SpringRunner.class)
@RestClientTest
@ContextConfiguration(classes = RestConfiguration.class)
public class TrouserRestRepositoryIT {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private TrouserRestRepository underTest;

    @Test
    public void findsTrousersInServer() {

        this.server.expect(requestTo("/trouser/1"))
                .andRespond(withSuccess("{\"id\":\"1\"}", APPLICATION_JSON_UTF8));

        Optional<Trouser> trouser = underTest.findBy(1L);

        assertEquals(Optional.of(new Trouser(1L)), trouser);
    }

    @Test
    public void notFindsTrousersInServer() {

        this.server.expect(requestTo("/trouser/1"))
                .andRespond(withStatus(NOT_FOUND));

        Optional<Trouser> trouser = underTest.findBy(1L);

        assertThat(trouser, is(empty()));
    }
}