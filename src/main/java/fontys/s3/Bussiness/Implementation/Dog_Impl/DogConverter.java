package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.Dog;
import fontys.s3.Persistence.Entity.DogEntity;

final class DogConverter {
    private DogConverter() {
    }

    public static Dog convert(DogEntity dogEntity) {
        return Dog.builder()
                .id(dogEntity.getId())
                .name(dogEntity.getName())
                .breed(dogEntity.getBreed())
                .age(dogEntity.getAge())
                .years(dogEntity.getYears())
                .build();
    }
}