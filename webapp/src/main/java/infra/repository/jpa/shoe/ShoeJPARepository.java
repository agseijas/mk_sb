package infra.repository.jpa.shoe;

import java.util.Optional;

import domain.shoes.Shoe;
import domain.shoes.ShoeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShoeJPARepository implements ShoeRepository {

    private final ShoeDAO dao;

    @Override
    public Optional<Shoe> findBy(Long id) {
        return dao.findById(id).map(this::adapt);
    }

    private Shoe adapt(ShoeEntity shoeEntity) {
        return new Shoe(shoeEntity.getId());
    }
}
