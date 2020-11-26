package com.football.service;

import com.football.client.TeamClientHandling;
import com.football.dto.GetTeamDataTransferObject;
import com.football.exception.BadRequestException;
import com.football.model.Country;
import com.football.model.GetTeam;
import com.football.model.Leagues;
import com.football.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GetTeamService {

    private final TeamClientHandling teamStandingRestClient;

    @Autowired
    public GetTeamService(TeamClientHandling teamStandingRestClient) {
        this.teamStandingRestClient = teamStandingRestClient;
    }

    public GetTeamDataTransferObject getTeamStanding(Request teamStandingRequest) {
        //Validate the request
        GetTeam teamStandingDefault = getDefaultTeamStanding(teamStandingRequest);
        List<Country> countries = getCountries();
        Country country = getCountryByName(teamStandingRequest, countries);
        if (!isValidateCountryResponse(teamStandingRequest, teamStandingDefault, country)) {
            return GetTeamDataTransferObject.getTeamData(teamStandingDefault);
        }
        teamStandingDefault.setId(country.getId());

        List<Leagues> leaguesList = getLeagues(country.getId());
        Leagues leagues = getLeaguesByName(teamStandingRequest, leaguesList);
        if (!isValidLeagueResponse(teamStandingRequest, teamStandingDefault, leagues)) {
            return(GetTeamDataTransferObject.getTeamData(teamStandingDefault));
        }
        teamStandingDefault.setLeagueId(leagues.getLeagueId());
        List<GetTeam> teamStandings = getTeamStanding(leagues.getLeagueId());


        GetTeam teamStandingsFiltered = getFilteredTeamStanding(teamStandingRequest,  teamStandings);
        teamStandingsFiltered.setId(country.getId());

        if (teamStandingsFiltered.getTeamId()==0){
            return GetTeamDataTransferObject.getTeamData(teamStandingDefault);
        }

        return GetTeamDataTransferObject.getTeamData(teamStandingsFiltered);
    }

    private Country getCountryByName(Request teamStandingRequest,
                                     List<Country> countries) {
        return countries.stream()
                .filter(c -> teamStandingRequest.getCountryName().equalsIgnoreCase(c.getName())).findFirst().orElse(null);
    }

    private Leagues getLeaguesByName(Request teamStandingRequest,
                                     List<Leagues> leaguesList) {
        return leaguesList.stream()
                .filter(l -> teamStandingRequest.getLeagueName().equalsIgnoreCase(l.getLeagueName()))
                .findFirst().orElse(null);
    }

    private GetTeam getFilteredTeamStanding(Request teamStandingRequest,
                                                 List<GetTeam> teamStandings) {
        return teamStandings.stream()
                .filter(t -> teamStandingRequest.getTeamName().equalsIgnoreCase(t.getTeamName()))
                .findFirst().orElse(null);
    }

    private boolean isValidLeagueResponse(Request teamStandingRequest,
                                          GetTeam teamStandingDefault, Leagues leagues) {
        if (Objects.isNull(leagues)) {
            throw new BadRequestException(
                    "leagues Not Found by name " + teamStandingRequest.getLeagueName());
        }

        if (leagues.getLeagueId() == 0) {
            return false;
        }
        return true;
    }

    private boolean isValidateCountryResponse(Request teamStandingRequest,
                                              GetTeam teamStandingDefault, Country country) {
        if (Objects.isNull(country)) {
            throw new BadRequestException(
                    "Country Not Found by name " + teamStandingRequest.getCountryName());
        }


        if (country.getId() == 0) {
            teamStandingDefault.setId(0);
            return false;
        }
        return true;
    }

    private GetTeam getDefaultTeamStanding(Request teamStandingRequest) {
        GetTeam teamStanding = new GetTeam();
        teamStanding.setTeamName(teamStandingRequest.getTeamName());
        teamStanding.setCountryName(teamStandingRequest.getCountryName());
        teamStanding.setLeagueName(teamStandingRequest.getLeagueName());
        return teamStanding;
    }

    private List<Country> getCountries() {
        return new ArrayList<>(Arrays.asList(teamStandingRestClient.getCountries()));
    }

    private List<Leagues> getLeagues(int countryId) {
        return new ArrayList<>(Arrays.asList(teamStandingRestClient.getLeagues(countryId)));
    }


    private List<GetTeam> getTeamStanding(int leagueId) {
        return new ArrayList<>(Arrays.asList(teamStandingRestClient.getTeamStanding(leagueId)));
    }
}
