package com.example.playerapiservice.entities;

import com.example.playerapiservice.enums.HandedType;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
This class can potentially be refactored into multiple smaller classes
 */
@Data
public class Player {
    private String playerID;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private HandedType bats;
    private HandedType throwsHand;
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate debut;
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;
}
