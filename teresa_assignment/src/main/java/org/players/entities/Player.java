package org.players.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @CsvBindByPosition(position = 0)
    private String playerID;
    @CsvBindByPosition(position = 1)
    private String birthYear;
    @CsvBindByPosition(position = 2)
    private String birthMonth;
    @CsvBindByPosition(position = 3)
    private String birthDay;
    @CsvBindByPosition(position = 4)
    private String birthCountry;
    @CsvBindByPosition(position = 5)
    private String birthState;
    @CsvBindByPosition(position = 6)
    private String birthCity;
    @CsvBindByPosition(position = 7)
    private String deathYear;
    @CsvBindByPosition(position = 8)
    private String deathMonth;
    @CsvBindByPosition(position = 9)
    private String deathDay;
    @CsvBindByPosition(position = 10)
    private String deathCountry;
    @CsvBindByPosition(position = 11)
    private String deathState;
    @CsvBindByPosition(position = 12)
    private String deathCity;
    @CsvBindByPosition(position = 13)
    private String nameFirst;
    @CsvBindByPosition(position = 14)
    private String nameLast;
    @CsvBindByPosition(position = 15)
    private String nameGiven;
    @CsvBindByPosition(position = 16)
    private String weight;
    @CsvBindByPosition(position = 17)
    private String height;
    @CsvBindByPosition(position = 18)
    private String bats;
    @CsvBindByPosition(position = 19)
    @JsonProperty("throws")
    private String thr;
    @CsvBindByPosition(position = 20)
    private String debut;
    @CsvBindByPosition(position = 21)
    private String finalGame;
    @CsvBindByPosition(position = 22)
    private String retroID;
    @CsvBindByPosition(position = 23)
    private String bbrefID;
}
