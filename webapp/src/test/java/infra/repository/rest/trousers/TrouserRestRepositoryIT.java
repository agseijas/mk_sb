package infra.repository.rest.trousers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import domain.trousers.Trouser;
import infra.RepositoryConfiguration.RestConfiguration;

@SpringBootTest(webEnvironment = NONE, classes = RestConfiguration.class)
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "trousers.uri=http://localhost:${wiremock.server.port}")
public class TrouserRestRepositoryIT {

    @Autowired
    private TrouserRestRepository underTest;

    @Test
    public void findsTrousersInServer() {

        stubFor(get(urlEqualTo("/trouser/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"id\": \"1\"}")));

        Optional<Trouser> trouser = underTest.findBy(1L);

        assertEquals(Optional.of(new Trouser(1L)), trouser);
    }

    @Test
    public void notFindsTrousersInServer() {
        stubFor(get(urlEqualTo("/trouser/1"))
                .willReturn(aResponse().withStatus(NOT_FOUND.value())));

        Optional<Trouser> trouser = underTest.findBy(1L);

        assertThat(trouser, is(empty()));
    }
}