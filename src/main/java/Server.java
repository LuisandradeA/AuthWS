import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.controller","com.repository"})
@EnableAutoConfiguration
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);  
 
    }
}