package com.football.controller;

import com.football.dto.GetTeamDataTransferObject;
import com.football.model.Request;
import com.football.service.GetTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/service/v1/team/standing")
public class FootBallController {

    @Autowired
    private final GetTeamService teamStandingService;

    @Autowired
    public FootBallController(GetTeamService teamStandingService) {
        this.teamStandingService = teamStandingService;
    }

    @GetMapping
    public ResponseEntity<GetTeamDataTransferObject> getStandings(@Validated Request teamStandingRequest) {
        return ResponseEntity.ok(teamStandingService.getTeamStanding(teamStandingRequest));
    }
}
