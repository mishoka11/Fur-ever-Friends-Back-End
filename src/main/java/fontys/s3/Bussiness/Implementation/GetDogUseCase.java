package fontys.s3.Bussiness.Implementation;
import fontys.s3.Domain.GetDogResponse;

public interface GetDogUseCase {
    GetDogResponse getDog(long dogId);
}