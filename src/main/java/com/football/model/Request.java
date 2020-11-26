package com.football.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Request {
    private String teamName;
    private String leagueName;
    private String countryName;
}
