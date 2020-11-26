package com.football.service;

import com.football.client.TeamClientHandling;
import com.football.dto.GetTeamDataTransferObject;
import com.football.exception.BadRequestException;
import com.football.model.Country;
import com.football.model.GetTeam;
import com.football.model.Request;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class GetTeamServiceTest {

    private final TeamClientHandling teamStandingRestClient;

    @Autowired
    public GetTeamServiceTest(TeamClientHandling teamStandingRestClient) {
        this.teamStandingRestClient = teamStandingRestClient;
    }

    @Autowired
    GetTeam getTeam;

    @Autowired
    GetTeamService getTeamService;

    @Autowired
    Request request;

    @Test
    void getTeamStanding() {
        /*Request request = new Request();*/
        /*request.setTeamName("Leeds");
        request.setLeagueName("Championship");
        request.setCountryName("England");
        List<GetTeam> teamStandings = getTeamService.getTeamStanding(leagues.getLeagueId());
        getTeamService.getTeamStanding(request,)

        GetTeamDataTransferObject getTeamDataTransferObject= getTeamService.getTeamStanding(request);*/
    }
}