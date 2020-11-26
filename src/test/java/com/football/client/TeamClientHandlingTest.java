package com.football.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.model.Country;
import com.football.model.GetTeam;
import com.football.model.Leagues;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TeamClientHandlingTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TeamClientHandling teamClientHandling;

    @Autowired
    private ObjectMapper objectMapper;

    private Resource expectedResult = new ClassPathResource("expectedCountry.json");

    @Test
    public void pullCountriesAndCheckNotNullAndCheckSize() throws IOException, JSONException {
        Country[] countries = teamClientHandling.getCountries();
        assertNotNull(countries);
        assertEquals(2, Arrays.stream(countries).count());
    }

    @Test
    public void pullLeaguesSuccessfulWithValidCountryId() throws IOException, JSONException {
        Country[] countries = teamClientHandling.getCountries();
        Arrays.stream(countries).flatMap(country -> {
            Leagues[] leagues = teamClientHandling.getLeagues(country.getId());
            assertNotNull(leagues);
            assertEquals(2, Arrays.stream(leagues).count());

            return null;
        });
    }

    @Test
    public void pullTeamForFirstCountryAndFirstLeagueSuccessfulWithValidCountryIdAndValidLeagueId() throws IOException, JSONException {
        Country[] countries = teamClientHandling.getCountries();
        Leagues[] leagues = teamClientHandling.getLeagues(Arrays.stream(countries).findFirst().get().getId());
        GetTeam[] getTeams = teamClientHandling.getTeamStanding(Arrays.stream(leagues).findFirst().get().getId());

        assertNotNull(countries);
        assertNotNull(leagues);
        assertNotNull(getTeams);
        assertEquals(1, Arrays.stream(leagues).count());
        assertEquals(2, Arrays.stream(countries).count());
        assertEquals(8, Arrays.stream(getTeams).count());
    }

}