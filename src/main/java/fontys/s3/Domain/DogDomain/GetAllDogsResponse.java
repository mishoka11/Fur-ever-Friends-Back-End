package fontys.s3.Domain.DogDomain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllDogsResponse {
    private List<Dog> dogs;
}
