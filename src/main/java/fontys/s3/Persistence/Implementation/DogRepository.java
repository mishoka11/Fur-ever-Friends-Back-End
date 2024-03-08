package fontys.s3.Persistence.Implementation;
import fontys.s3.Persistence.Entity.DogEntity;

import java.util.List;
import java.util.Optional;

public interface DogRepository {

    DogEntity save(DogEntity Dog);

    Optional<DogEntity> findById(long dogId);
    void deleteById(long dogId);
    List<DogEntity> findAll();
}
