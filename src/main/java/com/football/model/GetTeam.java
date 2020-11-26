package com.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GetTeam {

    @JsonProperty("country_id")
    private int id;
    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("league_id")
    private int leagueId;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("team_id")
    private int teamId;

    @JsonProperty("overall_league_position")
    private int position;

}
