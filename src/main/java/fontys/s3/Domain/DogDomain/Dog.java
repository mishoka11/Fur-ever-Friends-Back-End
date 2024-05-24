package fontys.s3.Domain.DogDomain;

import fontys.s3.Persistence.Entity.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dog {
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private Integer dogYears;
    private String information;
    private Size size;
}
