package fontys.s3.Persistence.Implementation.Repositories;


import fontys.s3.Persistence.Entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {
    UserTypeEntity findByTypeName(String typeName);
}
