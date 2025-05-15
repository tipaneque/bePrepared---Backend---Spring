package com.tipaneque.bePrepared.controller;

import com.tipaneque.bePrepared.dto.request.AlertRequest;
import com.tipaneque.bePrepared.dto.response.AlertResponse;
import com.tipaneque.bePrepared.mapper.Mapper;
import com.tipaneque.bePrepared.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alerts")
public class AlertController {
    private final Mapper mapper;
    private final AlertService alertService;

    @PostMapping("/")
    public ResponseEntity<String> createAlert(@Valid @RequestBody AlertRequest alertRequest){
        return new ResponseEntity<>(
                alertService.createAlert(
                        mapper.mapAlertRequestToModel(
                                alertRequest),
                                alertRequest.getCityId(),
                                alertRequest.getProvinceId()),
                        HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<AlertResponse>> getALlAlerts(){
        return ResponseEntity.ok(mapper.mapAlertToAlertResponseList(alertService.getAllAlerts()));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AlertResponse>> getAllActiveAlerts(){
        return ResponseEntity.ok(mapper.mapAlertToAlertResponseList(alertService.getAllActiveAlerts()));
    }

    @GetMapping("/city")
    public ResponseEntity<List<AlertResponse>> getallAlertsByCity(@RequestParam Long cityId){
        return ResponseEntity.ok(mapper.mapAlertToAlertResponseList(alertService.getAllAlertsByCityId(cityId)));
    }

    @GetMapping("/province")
    public ResponseEntity<List<AlertResponse>> getAllAlertsByProvince(@RequestParam Long provinceId){
        return ResponseEntity.ok(mapper.mapAlertToAlertResponseList(alertService.getAllAlertsByProvinceId(provinceId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertResponse> getAlertById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.mapAlertToAlertResponse(alertService.getAlertById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlert(@PathVariable Long id){
        return ResponseEntity.ok(alertService.activeAlert(id));
    }
}
