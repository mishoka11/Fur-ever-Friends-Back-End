package fontys.s3.Persistence.Implementation.Repositories;

import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogSizeRepository extends JpaRepository<DogSizeEntity, Long> {
    Optional<DogSizeEntity> findBySize(Size size);
}
