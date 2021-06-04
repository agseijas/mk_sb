package infra.repository.rest.trousers;

import static java.lang.Long.parseLong;
import static java.util.Optional.empty;

import java.util.Optional;

import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestTemplate;

import domain.trousers.Trouser;
import domain.trousers.TrouserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrouserRestRepository implements TrouserRepository {

    private final RestTemplate client;

    @Override
    public Optional<Trouser> findBy(Long id) {
        try {
            return Optional.ofNullable(client.getForEntity("/trouser/" + id, EndpointTrouser.class).getBody())
                    .map(this::adapt);
        } catch (NotFound e) {
            return empty();
        }
    }

    private Trouser adapt(EndpointTrouser trouser) {
        return new Trouser(parseLong(trouser.id));
    }

    private static class EndpointTrouser {
        public String id;
    }
}
