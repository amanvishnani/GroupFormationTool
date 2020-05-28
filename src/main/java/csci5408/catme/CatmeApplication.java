package csci5408.catme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class CatmeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CatmeApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
