package app;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ClosetApplication.class)
//@Transactional --- Currently @Transactional isn't working properly in reactive systems
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "trousers.uri=http://localhost:${wiremock.server.port}")
public class ClosetApplicationE2EIT {

    @Autowired
    WebTestClient client;

    @Test
    @Sql(statements = "INSERT INTO SHIRT (id) values (2);")
    public void dressesForWork() {
        stubFor(get(urlEqualTo("/trouser/1")).willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                        .withBody("{ \"id\": \"1\"}")));

        client.get().uri("/shirt/2").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 2}");

        client.get().uri("/trouser/1").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");
    }

    @Test
    @Sql(statements = "INSERT INTO SHIRT (id) values (3);")
    @Sql(statements = "INSERT INTO SHEET (id) values (3);")
    public void cannotGetDressedSoGoesBackToBed() {

        client.get().uri("/shirt/3").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 3}");

        client.get().uri("/trouser/1").exchange()
                .expectStatus().isNotFound();

        client.get().uri("/sheet/3").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 3}");
    }
}
