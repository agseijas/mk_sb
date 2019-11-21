package infra.repository.jpa.shirts;

import java.util.Optional;

import domain.shirts.Shirt;
import domain.shirts.ShirtRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShirtJPARepository implements ShirtRepository {

    private final ShirtDAO dao;

    @Override
    public Optional<Shirt> findBy(Long id) {
        return dao.findById(id).map(this::adapt);
    }

    private Shirt adapt(ShirtEntity shirtEntity) {
        return new Shirt(shirtEntity.getId());
    }
}
