package com.tipaneque.bePrepared.controller;

import com.tipaneque.bePrepared.dto.request.CitizenRequest;
import com.tipaneque.bePrepared.dto.response.CitizenResponse;
import com.tipaneque.bePrepared.mapper.Mapper;
import com.tipaneque.bePrepared.service.CitizenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/citizens")
public class CitizenController {
    private final Mapper mapper;
    private final CitizenService citizenService;

    @PostMapping("/")
    public ResponseEntity<String> createCitizen(@Valid @RequestBody CitizenRequest request){
        return new ResponseEntity<>(citizenService.createCitizen(mapper.mapCitizenRequestToCitizen(request), request.getCityId()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CitizenResponse>> getAllCitizens(){
        return ResponseEntity.ok(mapper.mapCitizenToCitizenResponseList(citizenService.getAllCitizens()));
    }

    @GetMapping("/city")
    public ResponseEntity<List<CitizenResponse>> getAllCitizensByCity(@RequestParam Long id){
        return ResponseEntity.ok(mapper.mapCitizenToCitizenResponseList(citizenService.getAllCitizensByCityId(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponse> getCitizenById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.mapCitizenToCitizenResponse(citizenService.getCitizenById(id)));
    }

    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccout(@RequestParam String otp){
        return ResponseEntity.ok(citizenService.verifyAccount(otp));
    }
}
