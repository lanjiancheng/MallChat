import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lan ChengJian, 2024/9/14
 */
@SpringBootApplication(scanBasePackages = {"cc"})
public class AppBoot {
    public static void main(String[] args) {
        SpringApplication.run(AppBoot.class, args);
    }
}
