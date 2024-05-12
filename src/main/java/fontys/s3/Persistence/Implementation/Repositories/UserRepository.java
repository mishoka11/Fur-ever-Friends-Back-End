package fontys.s3.Persistence.Implementation.Repositories;

import fontys.s3.Persistence.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(long userId);

    void deleteById(long userId);

    List<UserEntity> findAll();
}