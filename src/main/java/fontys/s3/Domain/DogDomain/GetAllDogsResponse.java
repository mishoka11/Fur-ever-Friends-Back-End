package fontys.s3.Domain.DogDomain;

import fontys.s3.Persistence.Entity.DogEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllDogsResponse {
    private List<DogEntity> dogs;
}