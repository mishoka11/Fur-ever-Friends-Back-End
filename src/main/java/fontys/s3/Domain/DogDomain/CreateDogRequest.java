package fontys.s3.Domain.DogDomain;

import fontys.s3.Persistence.Entity.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDogRequest {

    private String name;
    private String breed;
    private Integer age;
    private Integer years;
    private String information;
    private Size size;


}
