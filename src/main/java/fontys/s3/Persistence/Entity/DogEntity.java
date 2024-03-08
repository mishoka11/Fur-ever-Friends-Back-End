package fontys.s3.Persistence.Entity;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DogEntity {
    private Long id;
    private String name;
    private String breed;
    private int age;
    private int years;
}
