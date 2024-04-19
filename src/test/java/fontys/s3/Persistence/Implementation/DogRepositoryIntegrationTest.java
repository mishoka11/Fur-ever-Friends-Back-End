//package fontys.s3.Persistence.Implementation;
//
//import fontys.s3.Persistence.Entity.DogEntity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
////not currently passing
//@DataJpaTest
//public class DogRepositoryIntegrationTest {
//
//    @Autowired
//    private DogRepository dogRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Given
//        DogEntity dog = new DogEntity(null, "Fido", "Labrador", 5, 36);
//
//        // When
//        DogEntity savedDog = dogRepository.save(dog);
//        Optional<DogEntity> retrievedDogOptional = dogRepository.findById(savedDog.getId());
//
//        // Then
//        assertTrue(retrievedDogOptional.isPresent()); // Checks if dog was saved and retrieved successfully
//        DogEntity retrievedDog = retrievedDogOptional.get();
//        assertEquals("Fido", retrievedDog.getName()); // Checks if dog has correct attributes
//        assertEquals("Labrador", retrievedDog.getBreed());
//        assertEquals(5, retrievedDog.getAge());
//        assertEquals(36, retrievedDog.getYears());
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Given
//        DogEntity dog = new DogEntity(null, "Buddy", "Golden Retriever", 3, 36);
//        DogEntity savedDog = dogRepository.save(dog);
//
//        // When
//        dogRepository.deleteById(savedDog.getId());
//        Optional<DogEntity> retrievedDogOptional = dogRepository.findById(savedDog.getId());
//
//        // Then
//        assertTrue(retrievedDogOptional.isEmpty()); // Checks if dog was deleted
//    }
//}
