package com.example.umc_spring_first.domain.mission.controller;

import com.example.umc_spring_first.domain.mission.dto.MissionCreateRequest;
import com.example.umc_spring_first.domain.mission.service.MissionService;
import com.example.umc_spring_first.domain.mission.service.UserMissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final UserMissionService userMissionService;

    @PostMapping("/stores/{storeId}/missions")
    public ResponseEntity<?> addMission(
            @PathVariable Long storeId,
            @RequestBody MissionCreateRequest req
    ) {
        Long id = missionService.addMission(storeId, req);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/missions/{missionId}/challenge")
    public ResponseEntity<?> challengeMission(
            @PathVariable Long missionId
    ) {
        Long id = userMissionService.challengeMission(missionId);
        return ResponseEntity.ok(id);
    }
}
