package fontys.s3.Persistence.Implementation.Repositories;

import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<DogEntity, Long> {
    // Add custom query methods if needed
}
