package org.players;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfiguration
public class PlayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayersApplication.class, args);
    }

}
