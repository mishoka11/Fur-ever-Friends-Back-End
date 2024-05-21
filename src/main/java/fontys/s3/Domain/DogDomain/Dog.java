package fontys.s3.Domain.DogDomain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dog {
    private Long id;
    private String name;
    private String breed;
    private int age;
    private int years;
}
