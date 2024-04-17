package fontys.s3.Persistence.Implementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<DogEntity, Long> {

    DogEntity save(DogEntity Dog);

    Optional<DogEntity> findById(long dogId);
    void deleteById(long dogId);
    List<DogEntity> findAll();
}
