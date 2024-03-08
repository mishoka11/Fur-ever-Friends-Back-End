package fontys.s3.Persistence.Implementation;

import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class DogRepositoryImplementation implements DogRepository {
    private static long NEXT_ID = 1;
    private final List<DogEntity> savedDogs;

    public DogRepositoryImplementation() {
        this.savedDogs = new ArrayList<>();
    }
    @Override
    public DogEntity save(DogEntity dog) {
        if (dog.getId() == null) {
            dog.setId(NEXT_ID);
            NEXT_ID++;
            this.savedDogs.add(dog);
        }
        return dog;
    }
    @Override
    public Optional<DogEntity> findById(long dogId) {
        return this.savedDogs.stream()
                .filter(dogEntity -> dogEntity.getId().equals(dogId))
                .findFirst();
    }


    @Override
    public void deleteById(long dogId) {
        this.savedDogs.removeIf(dogEntity -> dogEntity.getId().equals(dogId));
    }
    @Override
    public List<DogEntity> findAll() {
        return Collections.unmodifiableList(this.savedDogs);
    }
 //scpraped for now (might implement in later versions)
   /* @Override
    public boolean existsByDogName(String dogName) {
        return this.savedDogs
                .stream()
                .anyMatch(dogEntity -> dogEntity.getDogName().equals(dogName));
    }

    @Override
    public List<DogEntity> findAllByBreed(String breed) {
        return this.savedDogs
                .stream()
                .filter(dogEntity -> dogEntity.getBreed().equals(breed))
                .toList();
    }


*/
}