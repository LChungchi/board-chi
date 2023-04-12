package idusw.springboot.boradthymleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //(exclude = DataSourceAutoConfiguration.class)
public class BoardChiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardChiApplication.class, args);
    }

}
