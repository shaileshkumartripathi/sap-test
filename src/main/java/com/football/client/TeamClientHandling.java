package com.football.client;


import com.football.config.RestClientConfig;
import com.football.model.Country;
import com.football.model.GetTeam;
import com.football.model.Leagues;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class TeamClientHandling {

    private static final String APIKEY = "APIkey";
    private final RestClientConfig restClientConfig;

    @Value("${football.base.url}")
    private String baseUrl;

    @Value("${football.teams}")
    private String standingsAction;

    @Value("${football.countries}")
    private String countriesAction;

    @Value("${football.leagues}")
    private String leaguesAction;

    @Value("${football.apiKey}")
    private String api;

    public TeamClientHandling(RestClientConfig restClientConfig) {
        this.restClientConfig = restClientConfig;
    }

    private RestTemplate getRestTemplate(){
        return restClientConfig.restTemplate();
    }


    public Country[] getCountries() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", countriesAction);
        UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
        return getRestTemplate()
                .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),
                        Country[].class).getBody();
    }

    private Country[] getCountries_Fallback() {
        return new Country[]{new Country()};
    }


    public Leagues[] getLeagues(int countryId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", leaguesAction);
        queryParams.put("country_id", String.valueOf(countryId));
        UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
        return getRestTemplate()
                .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),
                        Leagues[].class).getBody();
    }

    private Leagues[] getLeagues_Fallback(int countryId) {
        Leagues leagues = new Leagues();
        leagues.setId(countryId);
        return new Leagues[]{leagues};
    }

    public GetTeam[] getTeamStanding(int leagueId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", standingsAction);
        queryParams.put("league_id", String.valueOf(leagueId));
        UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
        return getRestTemplate()
                .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),
                        GetTeam[].class).getBody();
    }

    private GetTeam[] getTeamStanding_Fallback(int leagueId) {
        GetTeam getTeam = new GetTeam();
        getTeam.setLeagueId(leagueId);
        return new GetTeam[]{getTeam};
    }

    private UriComponentsBuilder getUriComponentsBuilder(String url,
                                                         Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(APIKEY, api);
        queryParams.forEach(builder::queryParam);
        return builder;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
