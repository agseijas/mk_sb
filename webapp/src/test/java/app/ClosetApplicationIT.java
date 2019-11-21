package app;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ClosetApplication.class)
@Transactional
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "trousers.uri=http://localhost:${wiremock.server.port}")
//THIS TEST IS ACTUALLY A CLOSETAPI INTEGRATION/E2E TEST
public class ClosetApplicationIT {

    @Autowired
    WebTestClient client;

    @Test
    @Sql(statements = "INSERT INTO SHIRT (id) values (1);")
    public void getsAShirtFromTheCloset() {
        client.get().uri("/shirt/1").exchange()

                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");
    }

    @Test
    //WITHOUT @Transactional THE TEST WILL FAIL BECAUSE OF THE PREVIOUS `INSERT` STATEMENTS
    public void notFindsAnyShirt() {
        client.get().uri("/shirt/1")
                .exchange()

                .expectStatus().isNotFound();
    }

    @Test
    @Sql(statements = "INSERT INTO SHEET (id) values (1);")
    public void getsASheetFromTheCloset() {
        client.get().uri("/sheet/1").exchange()

                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");
    }

    @Test
    public void notFindsAnySheet() {
        client.get().uri("/sheet/1")
                .exchange()

                .expectStatus().isNotFound();
    }

    @Test
    public void getsATrouserFromTheCloset() {
        stubFor(get(urlEqualTo("/trouser/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE)
                        .withBody("{ \"id\": \"1\"}")));

        client.get().uri("/trouser/1").exchange()

                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");
    }

    @Test
    public void notFindsAnyTrouser() {
        stubFor(get(urlEqualTo("/trouser/1"))
                .willReturn(aResponse().withStatus(NOT_FOUND.value())));

        client.get().uri("/trouser/1")
                .exchange()

                .expectStatus().isNotFound();
    }
}
