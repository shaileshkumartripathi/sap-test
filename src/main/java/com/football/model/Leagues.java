package com.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Leagues {

    @JsonProperty("country_id")
    private int id;
    @JsonProperty("country_name")
    private String name;

    @JsonProperty("league_id")
    private int leagueId;
    @JsonProperty("league_name")
    private String leagueName;
}
