package fontys.s3.Persistence.Entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
@Table(name = "dog")
public class DogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String breed;
    public int age;
    public int years;

    public DogEntity(Long id, String name, String breed, int age, int years) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.years = years;
    }

    public DogEntity() {

    }
}
