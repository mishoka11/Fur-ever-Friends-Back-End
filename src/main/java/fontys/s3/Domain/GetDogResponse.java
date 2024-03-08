package fontys.s3.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDogResponse {
    private Long dogId;
    private String name;
    private String breed;
    private int age;
    private int years;
}