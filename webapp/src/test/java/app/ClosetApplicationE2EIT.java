package app;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
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
@Transactional //AS WE ARE USING @Sql STATEMENTS/SCRIPTS WITH THIS IT WILL ROLLBACK THE DB CHANGES AFTER EACH TEST
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 0)
@TestPropertySource(properties = "trousers.uri=http://localhost:${wiremock.server.port}")
public class ClosetApplicationE2EIT {

    @Autowired
    WebTestClient client;

    @Test
    @Sql(statements = "INSERT INTO SHIRT (id) values (1);")
    public void dressesForWork() {
        stubFor(get(urlEqualTo("/trouser/1")).willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE)
                        .withBody("{ \"id\": \"1\"}")));

        client.get().uri("/shirt/1").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");

        client.get().uri("/trouser/1").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");
    }

    @Test
    @Sql(statements = "INSERT INTO SHIRT (id) values (1);")
    @Sql(statements = "INSERT INTO SHEET (id) values (1);")
    public void cannotGetDressedSoGoesBackToBed() {

        client.get().uri("/shirt/1").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");

        client.get().uri("/trouser/1").exchange()
                .expectStatus().isNotFound();

        client.get().uri("/sheet/1").exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().json("{ \"id\": 1}");
    }
}
