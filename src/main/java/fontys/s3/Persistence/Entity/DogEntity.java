package fontys.s3.Persistence.Entity;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DogEntity {
    public Long id;
    public String name;
    public String breed;
    public int age;
    public int years;

    // Public constructor explicitly defined
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
