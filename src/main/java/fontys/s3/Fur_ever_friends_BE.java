package fontys.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "fontys.s3")
@EnableJpaRepositories(basePackages = "fontys.s3.Persistence.Implementation.Repositories")
public class Fur_ever_friends_BE {

    public static void main(String[] args) {
        SpringApplication.run(Fur_ever_friends_BE.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/your_database_name_1");
        dataSource.setUsername("root");
        dataSource.setPassword("123m123M");
        return dataSource;
    }
}
