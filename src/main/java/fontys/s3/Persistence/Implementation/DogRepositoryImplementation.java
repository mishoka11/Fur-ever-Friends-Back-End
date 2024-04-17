package fontys.s3.Persistence.Implementation;

import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;





@Repository
public interface DogRepositoryImplementation extends JpaRepository<DogEntity, Long> {
    //  Add custom query methods here if needed
}