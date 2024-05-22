package fontys.s3.Domain.DogDomain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateDogResponse {
    private Long dogId;
}
