package fontys.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "fontys.s3.Persistence.Implementation")
public class DogCRUD {

    public static void main(String[] args) {
        SpringApplication.run(DogCRUD.class, args);
    }

}