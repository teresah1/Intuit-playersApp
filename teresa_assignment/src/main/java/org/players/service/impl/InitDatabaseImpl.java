package org.players.service.impl;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.players.entities.Player;
import org.players.exceptions.PlayerNotFoundException;
import org.players.repositories.PlayerRepository;
import org.players.service.InitDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitDatabaseImpl implements InitDatabase {
    @Autowired
    private Environment environment;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    @Bean
    public void fillData() throws IOException {
        String fileName = environment.getProperty("FILE_NAME");
        if (fileName == null || fileName.isEmpty()) {
            throw new PlayerNotFoundException("Missing value for FILE_NAME property");
        }

        File file = new ClassPathResource(fileName).getFile();
        try (Reader reader = new FileReader(file);) {
            ColumnPositionMappingStrategy<Player> strategy =
                    new ColumnPositionMappingStrategy<>();
            strategy.setType(Player.class);
            CsvToBean<Player> csvToBean =
                    new CsvToBeanBuilder<Player>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            Set<Player> players = new HashSet<>(csvToBean.parse());
            playerRepository.saveAll(players);
        }
    }
}

