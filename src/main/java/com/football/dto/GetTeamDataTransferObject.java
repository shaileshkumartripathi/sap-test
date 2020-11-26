package com.football.dto;

import com.football.model.GetTeam;
import lombok.Data;

import java.util.Objects;

@Data
public class GetTeamDataTransferObject {

    private String country;
    private String team;
    private int position;
    private String league;

    public static GetTeamDataTransferObject getTeamData(GetTeam getTeam){
        GetTeamDataTransferObject getTeamDataTransferObject= new GetTeamDataTransferObject();
        if(Objects.nonNull(getTeam)){
            getTeamDataTransferObject.setCountry("(" + getTeam.getId() + ") -" + getTeam.getCountryName());
            getTeamDataTransferObject.setLeague("(" + getTeam.getLeagueId() + ") -" + getTeam.getLeagueName());
            getTeamDataTransferObject.setTeam("(" + getTeam.getTeamId() + ") -" + getTeam.getTeamName());
            getTeamDataTransferObject.setPosition(getTeam.getPosition());
        }
        return getTeamDataTransferObject;
    }
}
